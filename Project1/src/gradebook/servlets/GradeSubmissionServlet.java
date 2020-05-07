package gradebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import gradebook.dto.UpdateGradeRequest;
import gradebook.models.Assignment;
import gradebook.models.Submission;
import gradebook.services.AssignmentService;

/**
 * Servlet implementation class GradeSubmissionServlet
 */
public class GradeSubmissionServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		AssignmentService as = new AssignmentService();
		if (session.getAttribute("assignment_id") == null) {
			resp.sendRedirect("course");
			return;
		}
		Integer assignmentId = (Integer)session.getAttribute("assignment_id");
		Assignment assignment = as.getAssignment(assignmentId);
		resp.getWriter().write((assignment.getPoints() + ""));
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		AssignmentService as = new AssignmentService();
		ObjectMapper om = new ObjectMapper();
		boolean successfulUpdate;
		
		if(req.getContentType().equals("application/json")) {
			int assignmentId = (int) session.getAttribute("assignment_id");
			String target = (String) session.getAttribute("target");
			UpdateGradeRequest ugr = om.readValue(req.getReader(), UpdateGradeRequest.class);
			double grade = ugr.getGrade();
			String comments = ugr.getComments();
		
			Submission submission = as.getSubmission(assignmentId, target);
			
			successfulUpdate = as.gradeSubmission(submission, grade, comments);
			
			resp.getWriter().write(successfulUpdate + "");
		}
	}

}
