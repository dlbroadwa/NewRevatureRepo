package gradebook.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AssignmentServlet
 */
public class AssignmentServlet extends HttpServlet {

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
			
			// Submission form
			out.println("<form enctype=\"multipart/form-data\" action=\"upload-submission\" method=\"POST\">\r\n" + 
					"           <input type=\"hidden\" name=\"MAX_FILE_SIZE\" value=\"100000\">\r\n" +
					"           <label for=\"file\" class=\"text\"><b>Choose a file to upload: </b></label>\r\n" +
					"           <input type=\"file\" name=\"uploaded_file\">\r\n" +
					"			<input type=\"submit\" value=\"Upload File\">\r\n" + 
					"		</form>");
		}
	}
}
