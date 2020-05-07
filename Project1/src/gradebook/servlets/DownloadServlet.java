package gradebook.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gradebook.models.Submission;
import gradebook.services.AssignmentService;

public class DownloadServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int assignment_id = Integer.parseInt(req.getParameter("assignment_id"));
		String student_id = req.getParameter("student_id");
		String fileName = req.getParameter("fileName");
		resp.setContentType("application/octet-stream");
		resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
		AssignmentService as = new AssignmentService();
		Submission submission = as.getSubmission(student_id, assignment_id);
		
		try(InputStream fileIn = submission.getFile();
		OutputStream out = resp.getOutputStream()) {
			byte[] buffer = new byte[1048];
			int bytesRead;
			while ((bytesRead = fileIn.read(buffer)) > 0) {
				out.write(buffer, 0, bytesRead);
			}
		}
	}

}
