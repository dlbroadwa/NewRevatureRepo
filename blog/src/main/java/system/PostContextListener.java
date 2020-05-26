package system;

import data.LocationRepository;
import data.PostRepository;
import data.UserRepository;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import service.LocationService;
import service.LocationServiceImp;
import service.PostService;
import service.PostServiceImp;
import service.UserService;
import service.UserServiceImp;
import utils.ConnectionUtils;
import utils.PostgresConnectionUtil;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Servlet context listener to read the db.properties file and pass credentials to the ConnectionUtil
 */
public class PostContextListener implements ServletContextListener {
    static final Logger logger = Logger.getLogger(PostContextListener.class);

    /**
     * Reads input from a db.properties file and instantiates ConnectionUtil, Repositories, and Services
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        // initialize log4j
        BasicConfigurator.configure();

        // rest of the context configuration
        Properties prop = new Properties();
        String propFileName = "db.properties";
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
        try {
            prop.load(inputStream);
        } catch (NullPointerException | IOException e) {
            logger.debug("db.properties not found");
        }
        ConnectionUtils connection = new PostgresConnectionUtil(prop.getProperty("url"),
                prop.getProperty("username"),prop.getProperty("password"));

        UserRepository userRepository = new UserRepository(connection);
        PostRepository postRepository = new PostRepository(connection);
        LocationRepository locationRepository = new LocationRepository(connection);
        PostService postService = new PostServiceImp(postRepository);
        UserService userService = new UserServiceImp(userRepository);
        LocationService locationService = new LocationServiceImp(locationRepository);

        //set context attributes
        context.setAttribute("postService",postService);
        context.setAttribute("userService",userService);
        context.setAttribute("locationService", locationService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
