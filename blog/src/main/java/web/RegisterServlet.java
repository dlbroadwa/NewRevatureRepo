package web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import service.UserService;
import utils.Regex;

/**
 * Servlet for registering a new user via POST method
 */
public class RegisterServlet extends HttpServlet {

	private UserService userService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("pages/registration.html");
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
		String password = jsonObject.get("password").getAsString();
		String password_confirm = jsonObject.get("password_confirm").getAsString();
		String first_name = jsonObject.get("first_name").getAsString();
		String last_name = jsonObject.get("last_name").getAsString();
		int success = 0;
		
		if (!Regex.isValidUserID(username)) {
			success = 1;
		} else if (userService.findByID(username) != null) {
			success = 2;
		} else if (!Regex.isValidPassword(password)) {
			success = 3;
		} else if (!password.equals(password_confirm)) {
			success = 4;
		} else if (!Regex.isValidName(first_name)) {
			success = 5;
		} else if (!Regex.isValidName(last_name)) {
			success = 6;
		} else {
			userService.createAccount(username, password, first_name, last_name);
		}
		resp.getWriter().write(success + "");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
	}

}
