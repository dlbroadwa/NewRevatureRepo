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
import gradebook.services.AssignmentService;
import gradebook.services.CourseService;

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
			CourseService cs = new CourseService();
			AssignmentService as = new AssignmentService();
			Course course = cs.getCourse(course_id);
			List<Assignment> assignments = as.getAssignments(course.getCourseId());
			
			out.println("<h2 class=\"titles\"><u>" + course.getCourseId() + ": " + course.getName() + "</u></h2><br>");
			out.println("<h3 class=\"text\"><u>Assignments</u></h3>");
			for (Assignment assignment : assignments) {
				out.println("<form action=\"assignment\" method=\"POST\">\r\n" + 
						"           <input type=\"hidden\" name=\"assignment_id\"value=\"" + assignment.getAssignmentID() + "\">\r\n" +
						"			<input type=\"submit\" value=\"" + assignment.getName() + ": " + assignment.getDueDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm a")) + "\">\r\n" + 
						"		</form>");
			}
		}
	}
}
