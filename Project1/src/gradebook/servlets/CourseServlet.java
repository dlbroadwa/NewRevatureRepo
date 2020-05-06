package gradebook.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gradebook.models.Assignment;
import gradebook.models.Course;
import gradebook.models.Teacher_User;
import gradebook.models.User;
import gradebook.services.AssignmentService;
import gradebook.services.CourseService;
import gradebook.services.LoginService;

public class CourseServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user_id = (String)(session.getAttribute("user_id"));
		String course_id = (String)(session.getAttribute("course_id"));
		if (user_id == null)
			resp.sendRedirect("index.html");
		else if (course_id == null)
			resp.sendRedirect("courses");
		else
			doPost(req,resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\"><title>Online Gradebook</title>");
		HttpSession session = req.getSession();
		String username = (String)(session.getAttribute("user_id"));
		String course_id = req.getParameter("course_id");
		if (course_id == null)
			course_id = (String)(session.getAttribute("course_id"));
		if (username == null)
			resp.sendRedirect("index.html");
		else if (course_id == null)
			resp.sendRedirect("courses");	
		else {
			session.setAttribute("course_id", course_id);
			LoginService ls = new LoginService();
			CourseService cs = new CourseService();
			AssignmentService as = new AssignmentService();
			User user = ls.getUser(username);
			Course course = cs.getCourse(course_id);
			List<Assignment> assignments = as.getAssignments(course.getCourseId());
			
			out.println("<h2 class=\"titles\"><u>" + course.getCourseId() + ": " + course.getName() + "</u></h2><br>");
			out.println("<form action=\"grades.html\" method=\"POST\">\r\n" + 
					"           <input type=\"hidden\" name=\"course_id\"value=\"" + course.getCourseId() + "\">\r\n" +
					"           <input type=\"hidden\" name=\"user_id\"value=\"" + username + "\">\r\n" +
					"			<input type=\"submit\" value=\"View Grades\">\r\n" + 
					"		</form>");
			out.println("<h3 class=\"text\"><u>Assignments</u></h3>");
			if (assignments.size() == 0)
				out.println("<p class=\"text\"><i>There are no assignments.</i></p>");
			for (Assignment assignment : assignments) {
				out.println("<form action=\"assignment\" method=\"POST\">\r\n" + 
						"           <input type=\"hidden\" name=\"assignment_id\"value=\"" + assignment.getAssignmentID() + "\">\r\n" +
						"			<input type=\"submit\" value=\"" + assignment.getName() + ": " + assignment.getDueDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm a")) + "\">\r\n" + 
						"		</form>");
			}
			if (user instanceof Teacher_User) {
				out.println("<br><form action=\"create-new-assignment.html\" method=\"POST\">\r\n" + 
						"           <input type=\"hidden\" name=\"course_id\"value=\"" + course.getCourseId() + "\">\r\n" +
						"			<input type=\"submit\" value=\"Create a new Assignment\">\r\n" + 
						"		</form>");
			}
		}
	}
}
