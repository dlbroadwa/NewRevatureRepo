package gradebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import gradebook.models.User;
import gradebook.services.ChangePasswordService;
import gradebook.services.LoginService;

/**
 * Servlet implementation class UpdatePasswordServlet
 */
public class UpdatePasswordServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ChangePasswordService cps = new ChangePasswordService();
		LoginService ls = new LoginService();
		ObjectMapper om = new ObjectMapper();
		
		if(req.getContentType().equals("application/json")) {
			String userId = (String) session.getAttribute("user_id");
			String newPassword = om.readValue(req.getReader(), String.class);
			
			User user = ls.getUser(userId);
			
			boolean successfulUpdate = cps.changePassword(user,newPassword);
			
			resp.getWriter().write(om.writeValueAsString(successfulUpdate));
		}
	}
}
