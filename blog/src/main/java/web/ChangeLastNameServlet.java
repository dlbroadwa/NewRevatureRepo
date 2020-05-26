package web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import model.User;
import service.UserService;
import utils.Regex;

/**
 * Servlet to handle Users changing their last name via POST request
 */
public class ChangeLastNameServlet extends HttpServlet {
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
		String last_name = jsonObject.get("last_name").getAsString();
		int success = 0;
		
		if (!Regex.isValidName(last_name)) {
			success = 1;
		} else {
			userService.changeLastName(last_name, user);
		}
		resp.getWriter().write(success + "");
    }
}
