package gradebook.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gradebook.models.Course;
import gradebook.services.CourseService;

public class CourseListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		String username = (String)(session.getAttribute("user_id"));
		// if invalid session, redirect to login
		if (username == null) {
			resp.sendRedirect("index.html");
		}
		CourseService cs = new CourseService();
		List<Course> courses = cs.getCourses(username);
		out.println("<h2>Welcome " + username + "!</h2>");
	}
}
