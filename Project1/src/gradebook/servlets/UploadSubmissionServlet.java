package gradebook.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import gradebook.services.AssignmentService;

/**
 * Servlet implementation class UploadSubmissionServlet
 */
@MultipartConfig
public class UploadSubmissionServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		int assignment_id = Integer.parseInt(req.getParameter("assignment_id"));
		String course_id = (String)(session.getAttribute("course_id"));
		String student_id = (String)(session.getAttribute("user_id"));
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		Part filePart = req.getPart("uploaded_file");
		if (filePart == null) {
			resp.sendRedirect("course");
			return;
		}
		Path path = Paths.get(filePart.getSubmittedFileName());
		String fileName = path.getFileName().toString();
		InputStream fileIn = null;
		
		try {
			fileIn = filePart.getInputStream();
			AssignmentService as = new AssignmentService();
			as.submitAssignment(assignment_id, course_id, student_id, fileIn, fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("assignment");
	}
}
