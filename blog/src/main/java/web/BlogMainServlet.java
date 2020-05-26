package web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import model.Post;
import org.apache.log4j.Logger;
import service.PostServiceImp;
import service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Servlet for the page showing recent blog posts
 */
public class BlogMainServlet extends HttpServlet {
    static final Logger logger = Logger.getLogger(BlogMainServlet.class);
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
        List<Post> recentPosts = postService.findRecent();
        String recentPostsJSON = "";
        Writer out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        Gson gson = new GsonBuilder().create();
        recentPostsJSON = gson.toJson(recentPosts);
        out.write(recentPostsJSON);
        out.flush();
        out.close();
    }
}
