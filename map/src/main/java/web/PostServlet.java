package web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import model.Post;
import service.PostService;
import service.PostServiceImp;


/**
 *
 * This is a Servlet that control or receive the request from the HTML
 *
 * It gets Users information base on the Location Such as City and Florida
 *
 * It gets the request from the AJAX Process Data and send it back to the Client side
 *
 * then client side will Manipulate the Dom then show it to users
 */

public class PostServlet extends HttpServlet {

	private PostService postService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gsonIn = new Gson();
		String jsonS = "";
		BufferedReader in = req.getReader();
		String inputLine;
		Writer out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		while((inputLine = in.readLine()) != null) {
			jsonS += inputLine;
		}
		JsonObject jsonObject = gsonIn.fromJson(jsonS, JsonObject.class);
		if(jsonObject == null)
		{
			out.write("");
			return;
		}
		if (jsonObject.get("city") == null || jsonObject.get("state") == null) {
			out.write("");
			return;
		}
		String city = jsonObject.get("city").getAsString();
		String state = jsonObject.get("state").getAsString();
		
		List<Post> posts = postService.findByCityState(city, state);
		String postsJSON = "";
		Gson gsonOut = new GsonBuilder().create();
		postsJSON = gsonOut.toJson(posts);
		out.write(postsJSON);
		out.flush();
		out.close();
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        postService = (PostServiceImp) context.getAttribute("postService");
	}

}
