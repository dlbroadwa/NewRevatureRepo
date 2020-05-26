package web;

import model.User;
import service.UserService;
import utils.Encryption;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;

/**
 * Servlet to process POST request for logging in the user
 */
public class LogInServlet extends HttpServlet {
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		String jsonS = "";
		BufferedReader in = req.getReader();
		String inputLine;
		while((inputLine = in.readLine()) != null) {
			jsonS += inputLine;
		}
		JsonObject jsonObject = gson.fromJson(jsonS, JsonObject.class);
		String username = jsonObject.get("username").getAsString();
		String password = Encryption.encrypt(jsonObject.get("password").getAsString());
		User user = userService.checkCredentials(username, password);
		if (user == null) {
			return;
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			String userJSON = "";
			Writer out = resp.getWriter();
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			Gson gsonOut = new GsonBuilder().create();
			userJSON = gsonOut.toJson(user);
			out.write(userJSON);
			out.flush();
			out.close();
		}
    }
}
