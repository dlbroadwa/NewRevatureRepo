package web;

import model.Post;
import model.User;
import org.apache.log4j.Logger;

import service.PostServiceImp;
import service.UserService;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Servlet for the User profile page
 */
public class ProfileServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(ProfileServlet.class);
    UserService userService;
    PostServiceImp postService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        userService = (UserService) context.getAttribute("userService");
        postService = (PostServiceImp) context.getAttribute("postService");
    }

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		User loggedInUser = (User)(session.getAttribute("user"));
		String user_id = req.getParameter("username");
		if (user_id == null) {
			resp.sendRedirect("blogMain.html");
			return;
		}
		User user = userService.findByID(user_id);
		// Head
		out.println("<head>\r\n" +
					"	 <link rel=\"stylesheet\" type=\"text/css\" href=\"stylesheet.css\">\r\n" +
					"    <title>Blog | " + user_id + "</title>\r\n" +
					"	 <meta charset=\"UTF-8\">\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\r\n" + 
					"    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootswatch/4.4.1/cosmo/bootstrap.min.css\">\r\n" + 
					"    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\r\n" + 
					"    <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js\"></script>");
		
		// Body
		out.println("<body>");
		if (user == null) {
			out.println("<h1>User: " + user_id + " Not Found.");
		} else {
			out.println("<div style=\"margin: 7px\">");
			out.println("<div id=\"DisplayInfo\" >\r\n" + 
						"    <h1><u><b>" + user.getFirstName() + " " + user.getLastName() + " - " + user.getUsername() + "</b></u></h1>\r\n" + 
						"    <br/>\r\n" + 
						"</div>");
			if (loggedInUser != null && loggedInUser.getUsername().equals(user_id)) {
				out.println("<div id=\"input_entry\">\r\n" + 
						"    <form method=\"post\">\r\n" + 
						"        <label><b>Change Password:</b></label><br>\r\n" + 
		        		"        <ul class=\"error\" id=\"error_message1\">\r\n" + 
		        		"			<li class=\"errorNum\" id=\"error1\">Old password is incorrect.</li>\r\n" + 
		        		"			<li class=\"errorNum\" id=\"error2\">Password must be at least 6 characters and contain no whitespace.</li>\r\n" + 
		        		"		 </ul>\r\n" +
						"        <input type=\"password\" id=\"old_password\" name=\"old_password\" placeholder=\"Enter old password\"/>\r\n" + 
						"        <input type=\"password\" id=\"new_password\" name=\"new_password\" placeholder=\"Enter new password\"/>\r\n" + 
						"        <button id=\"changePassword\">Change Password</button>\r\n" + 
						"    </form>\r\n" + 
						"    <form method=\"post\">\r\n" + 
						"        <label><b>Change First Name:</b></label><br>\r\n" + 
		        		"        <ul class=\"error\" id=\"error_message2\">\r\n" + 
		        		"			<li class=\"errorNum\" id=\"error3\">First name must be no longer than 20 alphabetic characters.</li>\r\n" + 
		        		"		 </ul>\r\n" +
						"        <input type=\"text\" id=\"first_name\" name=\"first_name\"  placeholder=\"Enter new first name\"/>\r\n" + 
						"        <button id=\"changeFirst\">Change First Name</button>\r\n" + 
						"    </form>\r\n" + 
						"    <form method=\"post\">\r\n" + 
						"        <label><b>Change Last Name:</b></label><br>\r\n" + 
		        		"        <ul class=\"error\" id=\"error_message3\">\r\n" + 
		        		"			<li class=\"errorNum\" id=\"error4\">Last name must be no longer than 20 alphabetic characters.</li>\r\n" + 
		        		"		 </ul>\r\n" +
						"        <input type=\"text\" id=\"last_name\" name=\"last_name\" placeholder=\"Enter new last name\"/>\r\n" + 
						"        <button id=\"changeLast\">Change Last Name</button>\r\n" + 
						"    </form>\r\n" + 
						"</div>\r\n");
			}	
			out.println("		<form action=\"pages/blogMain.html\">\r\n" + 
					"			<input type=\"submit\" value=\"Go back to home\" style=\"margin-top: 5px\">\r\n" + 
					"		</form>");
			out.println("<div id=\"PostInfo\">\r\n" + 
						"   <h1><u>Recent Posts</u></h1>\r\n" + 
						"   <div class=\"row row-cols-1 row-cols-md-3\" id=\"recent-posts-container\">");
			List<Post> posts = postService.findAllByUser(user_id);
			if (posts.isEmpty()) {
				out.println("<p style=\"margin-left: 20\">No recent posts.</p>");
			} else {
				for (Post post : posts) {
					out.println("<div class=\"card h-50\">");
					out.println("<img src=\"" + post.getURI() + "\" class=\"card-img-top\" alt=\"blog post image\">");
					out.println("<div class=\"card-body\">");
					out.println("<p class=\"card-text\" style=\"margin-bottom: 5\"><b>" + post.getTitle() + "</b></p>");
					out.println("<p class=\"card-text\" style=\"margin-bottom: 0\">" + post.getLocation().getName() + "</p>");
					out.println("<p class=\"card-text\"><i>" + post.getLocation().getCity() + ", " + post.getLocation().getState() + "</i></p>");
					out.println("<p class=\"card-text\">Rating: " + post.getRating() + "/5</p>");
					out.println("<p class=\"card-text\">" + post.getBody() + "</p>");
					out.println("</div>");
					out.println("</div>");
				}
			}				
			out.println("</div></div>");
			if (loggedInUser != null && loggedInUser.getUsername().equals(user_id)) {
				out.println("		<script type=\"text/javascript\">\r\n" + 
							"		    document.getElementById('changePassword').addEventListener('click', (e) => {\r\n" + 
							"		    	e.preventDefault();\r\n" + 
							"		    	let old_password = document.getElementById(\"old_password\").value;\r\n" + 
							"		    	let new_password = document.getElementById(\"new_password\").value;\r\n" + 
							"		    	const reqBody = {old_password, new_password};\r\n" + 
							"		    	const xhr0 = new XMLHttpRequest();\r\n" + 
							"		    	xhr0.onreadystatechange = () => {\r\n" + 
							"		    		if(xhr0.readyState === 4 && xhr0.status === 200) {\r\n" + 
							"	    				let updateStatus = xhr0.responseText;\r\n" + 
							"						document.getElementById(\"error_message1\").style.display = \"none\";\r\n" + 
							"	    				document.getElementById(\"error1\").style.display = \"none\";\r\n" + 
							"	    				document.getElementById(\"error2\").style.display = \"none\";\r\n" + 
							"	    				if(updateStatus == '0'){\r\n" + 
							"	    					window.location.href = \"pages/blogMain.html\";\r\n" + 
							"	    				} else {\r\n" + 
							"	    					document.getElementById(\"error_message1\").style.display = \"block\";\r\n" + 
							"	    					if (updateStatus == '1')\r\n" + 
							"	    						document.getElementById(\"error1\").style.display = \"block\";\r\n" + 
							"	    					else if (updateStatus == '2')\r\n" + 
							"	    						document.getElementById(\"error2\").style.display = \"block\";\r\n" + 
							"	    				}\r\n" + 
							"		    		}\r\n" + 
							"		    	};\r\n" + 
							"		    	xhr0.open(\"POST\", \"change_password\");\r\n" + 
							"		    	xhr0.setRequestHeader(\"Content-Type\", \"application/json\");\r\n" + 
							"		    	xhr0.send(JSON.stringify(reqBody));\r\n" + 
							"		    });\r\n" + 
							"		</script>");
				
				out.println("		<script type=\"text/javascript\">\r\n" + 
						"		    document.getElementById('changeFirst').addEventListener('click', (e) => {\r\n" + 
						"		    	e.preventDefault();\r\n" + 
						"		    	let first_name = document.getElementById(\"first_name\").value;\r\n" + 
						"		    	const reqBody = {first_name};\r\n" + 
						"		    	const xhr0 = new XMLHttpRequest();\r\n" + 
						"		    	xhr0.onreadystatechange = () => {\r\n" + 
						"		    		if(xhr0.readyState === 4 && xhr0.status === 200) {\r\n" + 
						"	    				let updateStatus = xhr0.responseText;\r\n" + 
						"						document.getElementById(\"error_message2\").style.display = \"none\";\r\n" + 
						"	    				document.getElementById(\"error3\").style.display = \"none\";\r\n" + 
						"	    				if(updateStatus == '0'){\r\n" + 
						"	    					window.location.href = \"profile?username=" + user_id + "\";\r\n" + 
						"	    				} else {\r\n" + 
						"	    					document.getElementById(\"error_message2\").style.display = \"block\";\r\n" + 
						"	    					if (updateStatus == '1')\r\n" + 
						"	    						document.getElementById(\"error3\").style.display = \"block\";\r\n" + 
						"	    				}\r\n" + 
						"		    		}\r\n" + 
						"		    	};\r\n" + 
						"		    	xhr0.open(\"POST\", \"change_first_name\");\r\n" + 
						"		    	xhr0.setRequestHeader(\"Content-Type\", \"application/json\");\r\n" + 
						"		    	xhr0.send(JSON.stringify(reqBody));\r\n" + 
						"		    });\r\n" + 
						"		</script>");
				
				out.println("		<script type=\"text/javascript\">\r\n" + 
						"		    document.getElementById('changeLast').addEventListener('click', (e) => {\r\n" + 
						"		    	e.preventDefault();\r\n" + 
						"		    	let last_name = document.getElementById(\"last_name\").value;\r\n" + 
						"		    	const reqBody = {last_name};\r\n" + 
						"		    	const xhr0 = new XMLHttpRequest();\r\n" + 
						"		    	xhr0.onreadystatechange = () => {\r\n" + 
						"		    		if(xhr0.readyState === 4 && xhr0.status === 200) {\r\n" + 
						"	    				let updateStatus = xhr0.responseText;\r\n" + 
						"						document.getElementById(\"error_message3\").style.display = \"none\";\r\n" + 
						"	    				document.getElementById(\"error4\").style.display = \"none\";\r\n" + 
						"	    				if(updateStatus == '0'){\r\n" + 
						"	    					window.location.href = \"profile?username=" + user_id + "\";\r\n" + 
						"	    				} else {\r\n" + 
						"	    					document.getElementById(\"error_message3\").style.display = \"block\";\r\n" + 
						"	    					if (updateStatus == '1')\r\n" + 
						"	    						document.getElementById(\"error4\").style.display = \"block\";\r\n" + 
						"	    				}\r\n" + 
						"		    		}\r\n" + 
						"		    	};\r\n" + 
						"		    	xhr0.open(\"POST\", \"change_last_name\");\r\n" + 
						"		    	xhr0.setRequestHeader(\"Content-Type\", \"application/json\");\r\n" + 
						"		    	xhr0.send(JSON.stringify(reqBody));\r\n" + 
						"		    });\r\n" + 
						"		</script>");
			}
		}
	}

}
