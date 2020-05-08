package gradebook.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import gradebook.dto.LoginRequest;
import gradebook.models.Student_User;
import gradebook.models.Teacher_User;
import gradebook.models.User;
import gradebook.services.LoginService;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("index.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		session = req.getSession();
		LoginService ls = new LoginService();
		ObjectMapper om = new ObjectMapper();
		boolean successfulLogin = false;
		
		if (req.getContentType().equals("application/json")) {
			LoginRequest lr = om.readValue(req.getReader(), LoginRequest.class);
			String userId = lr.getUserId();
			String userPass = lr.getUserPass();
			User user = ls.validate(userId, userPass);
			if (user != null) {
				session.setAttribute("user_id", userId);
				if (user instanceof Student_User)
					session.setAttribute("isStudent", true);
				else if (user instanceof Teacher_User)
					session.setAttribute("isTeacher", true);
				successfulLogin = true;
			}
			resp.getWriter().write(successfulLogin + "");
		}
	}
}
