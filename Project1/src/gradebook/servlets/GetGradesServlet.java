package gradebook.servlets;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import gradebook.dto.GradeDisplay;
import gradebook.dto.GradeWrapper;
import gradebook.models.Assignment;
import gradebook.models.Submission;
import gradebook.services.AssignmentService;

/**
 * Servlet implementation class GetGradesServlet
 */
public class GetGradesServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = (String) session.getAttribute("user_id");
		String courseId = (String) session.getAttribute("course_id");
		ObjectMapper om = new ObjectMapper();
		if(userId != null && courseId != null) {
			boolean submittedAssignment;
			AssignmentService as = new AssignmentService();
			List<Submission> submissions = as.getSubmissions(userId, courseId);
			List<Assignment> assignments = as.getAssignments(courseId);
			GradeWrapper gradesList = new GradeWrapper();
			
			for(Assignment a: assignments) {
				submittedAssignment = false;
				for(Submission s: submissions) {
					if(a.getAssignmentID() == s.getAssignmentId() && s.getPoints() >= 0) {
						GradeDisplay gd = new GradeDisplay(a.getName(),Integer.toString(s.getPoints()),Integer.toString(a.getPoints()),
								s.getDateSubmitted().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm a")),
								a.getDueDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm a")), a.getAssignmentID());
						gradesList.grades.add(gd);
						submittedAssignment = true;
						break;
					}
					else if(a.getAssignmentID() == s.getAssignmentId()) {
						GradeDisplay gd = new GradeDisplay(a.getName(),"-",Integer.toString(a.getPoints()),
								s.getDateSubmitted().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm a")),
								a.getDueDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm a")), a.getAssignmentID());
						gradesList.grades.add(gd);
						submittedAssignment = true;
						break;
					}
				}
				if(!submittedAssignment) {
					GradeDisplay gd = new GradeDisplay(a.getName(),"-",Integer.toString(a.getPoints()),
							"Not Submitted",a.getDueDate().format(DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm a")),
							a.getAssignmentID());
					gradesList.grades.add(gd);
				}
			}
			
			String test = om.writeValueAsString(gradesList);
			resp.getWriter().write(test);
		}
	}	
}
