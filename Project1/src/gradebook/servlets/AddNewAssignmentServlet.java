package gradebook.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gradebook.models.Teacher_User;
import gradebook.models.User;
import gradebook.services.AssignmentService;
import gradebook.services.LoginService;

public class AddNewAssignmentServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String user_id = (String)(session.getAttribute("user_id"));
		String course_id = (String)(session.getAttribute("course_id"));
		LoginService ls = new LoginService();
		User user = ls.getUser(user_id);
		if (user instanceof Teacher_User && course_id != null) {
			AssignmentService as = new AssignmentService();
			String stringDate = req.getParameter("date");
			LocalDateTime date = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")).atTime(23, 59, 59);
			as.createAssignment(course_id, req.getParameter("title"), req.getParameter("body"), Integer.parseInt(req.getParameter("points")), date);
			resp.sendRedirect("course");
		} else {
			resp.sendRedirect("courses");
		}
	}

}
