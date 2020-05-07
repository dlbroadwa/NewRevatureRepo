package gradebook.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gradebook.models.User;
import gradebook.services.CourseService;

public class RosterServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("course");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\"><title>Online Gradebook</title>");
		HttpSession session = req.getSession();
		String course_id = (String)(session.getAttribute("course_id"));
		if (course_id == null) {
			resp.sendRedirect("course");
			return;
		} else {
			CourseService cs = new CourseService();
			List<User> students = cs.getStudentsInCourse(course_id);
			out.println("<table style=\"width: 500px;\">");
			out.println("<tr><th>" + course_id + " Class Roster</th></tr>");
			for (User student : students) {			
				out.println("<tr><td>" + student.getFirstName() + " " + student.getLastName() + " - " + student.getEmail() + "</td></tr>");
			}
			out.println("</table>");
		}
	}

}
