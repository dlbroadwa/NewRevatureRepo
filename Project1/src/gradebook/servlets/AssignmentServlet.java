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
			resp.sendRedirect("course");
		} else {
			doPost(req, resp);
		}		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<head><link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\"><title>Online Gradebook</title>");
		HttpSession session = req.getSession();
		int assignment_id;
		String username = (String)(session.getAttribute("user_id"));
		String course_id = (String)(session.getAttribute("course_id"));
		String param = req.getParameter("assignment_id");
		if (param == null)
			assignment_id = (Integer)(session.getAttribute("assignment_id"));
		else
			assignment_id = Integer.parseInt(param);
		if (course_id == null || assignment_id <= 0)
			resp.sendRedirect("courses");	
		else {
			session.setAttribute("assignment_id", assignment_id);
			LoginService ls = new LoginService();
			AssignmentService as = new AssignmentService();
			Submission submission = null;
			User user = ls.getUser(username);
			
			Assignment assignment = as.getAssignment(assignment_id);
			String points = "-";
			if (user instanceof Student_User) {
				submission = as.getSubmission(assignment_id, username);
				if (submission != null && submission.getPoints() > -1)
					points = submission.getPoints() + "";
			}
			out.println("<div class=\"box\">");
			out.println("<h1 class=\"titles\" style=\"line-height: 15px;\"><u>" + assignment.getName() + "</u></h2>");
			out.println("<p class=\"text\" style=\"white-space: pre-wrap;\">" + assignment.getBody() + "</p><br>");
			out.println("<p class=\"text\"><b>Points:</b> " + points + "/" + assignment.getPoints() + "</p>");
			out.println("<p class=\"text\"><b>Due Date:</b> " + assignment.getDueDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy hh:mm a")) + "</p>");
			if (submission != null && submission.getComments() != null) {
				out.println("<p class=\"text\" style=\"white-space: pre-wrap;\"><b>Comments:</b> " + submission.getComments() + "</p>");
			}
			out.println("<br>");
			// Submission form
			if (user instanceof Student_User) {
				out.println("<div style=\"display: inline-block; border: 1px solid black; background-color: rgb(232,232,232); padding: 7px 15px\">");
				out.println("<form enctype=\"multipart/form-data\" action=\"upload-submission\" method=\"POST\" style=\"margin: 3px 0px;\">\r\n" + 
						"           <input type=\"hidden\" name=\"assignment_id\" value=\"" + assignment_id +"\">\r\n" +
						"           <label for=\"file\" class=\"text\"><b>Choose a file to upload: </b></label>\r\n" +
						"           <input type=\"file\" name=\"uploaded_file\">\r\n" +
						"			<input class=\"square\" type=\"submit\" value=\"Upload File\">\r\n" + 
						"		</form>");
				if (submission != null) {
					out.println("<p class=\"text\" style=\"color: green; margin: 8px 0px;\"><i><b>Successfully Submitted</b></i></p>");
				}
				out.println("</div>");
			}
			if (user instanceof Teacher_User) {
				List<Submission> submissions = as.getSubmissions(assignment_id);
				for (Submission sub : submissions) {
					User student = ls.getUser(sub.getStudentId());
					out.println("<div style=\"display: inline-block; border: 1px solid black; background-color: rgb(232,232,232); padding: 7px 15px\">");
					out.print("<div class=\"text\" style=\"display: inline-block; margin: 3px 0px;\"><b>" + student.getFirstName() + " " + student.getLastName() + ":</b></div>&nbsp;");
					out.println("<form action=\"download\" method=\"POST\" style=\"display: inline-block; margin: 3px 0px;\">\r\n" + 
							"           <input type=\"hidden\" name=\"assignment_id\"value=\"" + sub.getAssignmentId() + "\">\r\n" +
							"           <input type=\"hidden\" name=\"student_id\"value=\"" + sub.getStudentId() + "\">\r\n" +
							"           <input type=\"hidden\" name=\"fileName\"value=\"" + sub.getFileName() + "\">\r\n" +
							"			<input class=\"square\" type=\"submit\" value=\"Download\">\r\n" + 
							"		</form>");
					out.println("<form action=\"student-target\" method=\"POST\" style=\"display: inline-block; margin: 3px 0px;\">\r\n" + 
							"           <input type=\"hidden\" name=\"student_id\"value=\"" + sub.getStudentId() + "\">\r\n" +
							"			<input class=\"square\" type=\"submit\" value=\"Grade\">\r\n" + 
							"		</form>");
					if (sub.getPoints() > -1) {
						out.println("<p class=\"text\" style=\"color: green; margin: 8px 0px;\"><i><b>Successfully Graded</b></i></p>");
					} else {
						out.println("<p class=\"text\" style=\"color: red; margin: 8px 0px;\"><i><b>Not Graded</b></i></p>");
					}
					out.println("</div><br><br>");
				}
			}
			out.println("</div>");
		}
	}
}
