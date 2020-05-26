package web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.Location;
import model.User;
import service.LocationService;
import service.PostService;

/**
 * Servlet to process POST request in creating a new Post object
 */
@MultipartConfig
public class CreatePostServlet extends HttpServlet {

	private PostService ps;
	private LocationService ls;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ps = (PostService) context.getAttribute("postService");
        ls = (LocationService) context.getAttribute("locationService");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		if (session.getAttribute("user") == null){
            resp.sendRedirect("pages/blogMain.html");
            return;
        }	
		User user = (User)session.getAttribute("user");
		String name = req.getParameter("location");
		String street_number = req.getParameter("address");
		String route = req.getParameter("route");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String zip = req.getParameter("zip");
		String country = req.getParameter("country");
		float latitude = Float.parseFloat(req.getParameter("latitude"));
		float longitude = Float.parseFloat(req.getParameter("longitude"));
		String title = req.getParameter("title");
		String body = req.getParameter("body");
		int rating = Integer.parseInt(req.getParameter("rating"));
		Part imagePart = req.getPart("image");
		String fileName = null;
		InputStream fileIn = null;
		if (imagePart != null) {
			Path path = Paths.get(imagePart.getSubmittedFileName());
			fileName = path.getFileName().toString();			
			try {
				fileIn = imagePart.getInputStream();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		ls.createLocation(name, street_number, route, city, state, zip, country, latitude, longitude);
		Location location = ls.getLocation(name, street_number, route, city, state);
		ps.createNewPost(user.getUsername(), body, rating, fileName, fileIn, location, title);
		resp.sendRedirect("pages/blogMain.html");
	}
}
