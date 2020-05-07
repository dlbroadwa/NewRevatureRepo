package gradebook.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import gradebook.dto.UpdatePasswordRequest;
import gradebook.models.User;
import gradebook.services.ChangePasswordService;
import gradebook.services.LoginService;
import gradebook.util.Encryption;

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
		boolean successfulUpdate = false;
		
		if(req.getContentType().equals("application/json")) {
			String userId = (String) session.getAttribute("user_id");
			UpdatePasswordRequest upr = om.readValue(req.getReader(), UpdatePasswordRequest.class);
			String oldPassword = upr.getOldPassword();
			String newPassword1 = upr.getNewPassword1();
			String newPassword2 = upr.getNewPassword2();
			
			User user = ls.getUser(userId);
			if (user.getPassword().equals(Encryption.encrypt(oldPassword)) && newPassword1.equals(newPassword2)) {
				successfulUpdate = cps.changePassword(user,newPassword1);
			}
			if (successfulUpdate)
				session.invalidate();	
			resp.getWriter().write(successfulUpdate + "");
		}
	}
}
