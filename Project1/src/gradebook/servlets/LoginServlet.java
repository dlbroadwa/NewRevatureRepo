package gradebook.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gradebook.util.Regex;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		boolean validUsername = Regex.isValidUserID(username);
		resp.setContentType("text/html");
//		PrintWriter out = resp.getWriter();
//		String docType = "<!DOCTYPE html>";
//		if (validUsername) {
//			out.println(docType + 
//					"<html>\n" +
//						"<head><title>Login</title></head>\n" +
//						"<body>\n" +
//							"<h1>Login Result</h1>\n" +
//							"<ul>\n" +
//								"<li>" + validUsername + "</li>\n" +
//								"<li>" + password + "</li>\n" +
//							"</ul>\n" +
//						"</body>" +
//					"</html>"
//				);
//		} else {
//			out.println(docType + 
//					"<html>\n" +
//						"<head><title>Login</title></head>\n" +
//						"<body>\n" +
//							"<h1>Login Result</h1>\n" +
//							"<ul>\n" +
//								"<li>bad username!!</li>\n" +
//								"<li>" + password + "</li>\n" +
//							"</ul>\n" +
//						"</body>" +
//					"</html>"
//				);
//		}
	}
}
