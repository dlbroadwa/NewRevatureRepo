package gradebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gradebook.services.AssignmentService;

/**
 * Servlet implementation class AssignmentServlet
 */
public class AssignmentServlet extends HttpServlet {
	AssignmentService as = new AssignmentService();
	
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
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("user_id");
		String courseId = (String)session.getAttribute("course_id");
		int assignmentId = Integer.parseInt(req.getParameter("assignment_id"));
		
		
		
		session.setAttribute("assignment_id", assignmentId);
	}
}
