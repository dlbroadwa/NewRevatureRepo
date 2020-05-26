package web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Post;
import service.PostService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

/**
 * Gets the blogs at a given location
 * Posts the blogs at a given location
 */
public class BlogAtLocationServlet extends HttpServlet {
    private PostService postService;

    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        postService = (PostService) context.getAttribute("postService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int location_id = Integer.parseInt(req.getParameter("location_id"));

        List<Post> postList = postService.findAllPostsAtLocation(location_id);
        String postJSON = "";
        Writer out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new GsonBuilder().create();
        postJSON = gson.toJson(postList);
        out.write(postJSON);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.isRequestedSessionIdValid()){
            resp.sendRedirect("index.html");
            return;
        }
        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");
        String body = req.getParameter("body");
        int rating = Integer.parseInt(req.getParameter("rating"));
        String title = req.getParameter("title");
        //figure out how to get an image
        String imageFileName = null;
        InputStream image = null;
        int location_id = Integer.parseInt(req.getParameter("location_id"));
        String content = req.getParameter("messageSend");
        if (content==null){
            content="";
        }
        //postService.createNewPost(username, body, rating, imageFileName, image, location_id, title);
        //if we want website-wide messaging, include url from the request
        //resp.sendRedirect("pages/portal.html");
    }
}
