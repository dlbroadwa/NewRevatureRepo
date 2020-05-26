package web;

import model.User;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import service.UserService;
import utils.Encryption;
import utils.Regex;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Servlet to handle Users changing their first name via POST request
 */
public class ChangeFirstNameServlet extends HttpServlet {
    UserService userService;
    static final Logger logger = Logger.getLogger(ChangeFirstNameServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("user");
		if (user == null) {
			resp.sendRedirect("pages/blogMain.html");
			return;
		} 	
    	Gson gson = new Gson();
		String jsonS = "";
		BufferedReader in = req.getReader();
		String inputLine;
		while((inputLine = in.readLine()) != null) {
			jsonS += inputLine;
		}
		JsonObject jsonObject = gson.fromJson(jsonS, JsonObject.class);
		String first_name = jsonObject.get("first_name").getAsString();
		int success = 0;
		
		if (!Regex.isValidName(first_name)) {
			success = 1;
		} else {
			userService.changeFirstName(first_name, user);
		}
		resp.getWriter().write(success + "");
    }
}
