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
import gradebook.models.User;
import gradebook.services.CourseService;
import gradebook.services.LoginService;

public class CourseListServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("index.html");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\"><title>Online Gradebook</title>");
		HttpSession session = req.getSession();
		String username = (String)(session.getAttribute("user_id"));
		// if invalid session, redirect to login
		if (username == null) {
			resp.sendRedirect("index.html");
		} else {
			CourseService cs = new CourseService();
			LoginService ls = new LoginService();
			User user = ls.getUser(username);
			List<Course> courses = cs.getCourses(username);
			out.println("<div class=\"box\">");
			out.println("<h2 class=\"titles\"><u>" + user.getFirstName() + " " + user.getLastName() + "'s Courses</u></h2>");
			if (courses.size() == 0)
				out.println("<p class=\"text\"><i>You have no courses.</i></p>");
			for (Course course : courses) {
				out.println("<form action=\"course\" method=\"POST\">\r\n" + 
						"           <input type=\"hidden\" name=\"course_id\"value=\"" + course.getCourseId() + "\">\r\n" +
						"			<input class=\"round\" type=\"submit\" value=\"" + course.getCourseId() + ": " + course.getName() + "\">\r\n" + 
						"		</form>");
			}
			out.println("<br><form action=\"change-password.html\" method=\"POST\">\r\n" + 
					"			<input class=\"square\" type=\"submit\" value=\"Reset Password\">\r\n" + 
					"		</form>");
			out.println("</div>");
		}
	}
}
