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

import gradebook.models.Student_User;
import gradebook.models.Submission;
import gradebook.models.Teacher_User;
import gradebook.models.User;
import gradebook.services.AssignmentService;
import gradebook.services.LoginService;

/**
 * Servlet implementation class AssignmentServlet
 */
public class AssignmentServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Integer assignmentId = (Integer)session.getAttribute("assignment_id");
		if(assignmentId == null) {
			resp.getWriter().write("400");
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\"><title>Online Gradebook</title>");
		HttpSession session = req.getSession();
		String username = (String)(session.getAttribute("user_id"));
		String course_id = (String)(session.getAttribute("course_id"));
		int assignment_id = Integer.parseInt(req.getParameter("assignment_id"));
		if (course_id == null || assignment_id <= 0)
			resp.sendRedirect("courses");	
		else {
			LoginService ls = new LoginService();
			AssignmentService as = new AssignmentService();
			User user = ls.getUser(username);
			// Submission form
			if (user instanceof Student_User) {
				out.println("<form enctype=\"multipart/form-data\" action=\"upload-submission\" method=\"POST\">\r\n" + 
						"           <input type=\"hidden\" name=\"assignment_id\" value=\"" + assignment_id +"\">\r\n" +
						"           <label for=\"file\" class=\"text\"><b>Choose a file to upload: </b></label>\r\n" +
						"           <input type=\"file\" name=\"uploaded_file\">\r\n" +
						"			<input type=\"submit\" value=\"Upload File\">\r\n" + 
						"		</form>");
			}
			if (user instanceof Teacher_User) {
				List<Submission> submissions = as.getSubmissions(assignment_id);
				for (Submission submission : submissions) {
					out.println("<form action=\"download\" method=\"POST\">\r\n" + 
							"           <input type=\"hidden\" name=\"assignment_id\"value=\"" + submission.getAssignmentId() + "\">\r\n" +
							"           <input type=\"hidden\" name=\"student_id\"value=\"" + submission.getStudentId() + "\">\r\n" +
							"           <input type=\"hidden\" name=\"fileName\"value=\"" + submission.getFileName() + "\">\r\n" +
							"			<input type=\"submit\" value=\"" + submission.getStudentId() + ": submission\">\r\n" + 
							"		</form>");
				}
			}
		}
	}
}
