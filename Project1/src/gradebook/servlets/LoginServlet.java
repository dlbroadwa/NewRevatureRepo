package gradebook.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		session.invalidate();
		session = req.getSession();
		String username = req.getParameter("user_id");
		String password = req.getParameter("user_password");
		LoginService ls = new LoginService();
		User user = ls.validate(username, password);
		if(user != null) {
			session.setAttribute("user_id", username);
			if (user instanceof Student_User)
				session.setAttribute("isStudent", true);
			else if (user instanceof Teacher_User)
				session.setAttribute("isTeacher", true);
			resp.sendRedirect("courses");
		} else {
			resp.sendRedirect("index.html");
		}
	}
}
