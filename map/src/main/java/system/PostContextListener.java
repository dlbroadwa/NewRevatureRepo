package system;

import data.PostRepository;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import service.PostService;
import service.PostServiceImp;
import utils.ConnectionUtils;
import utils.PostgresConnectionUtil;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class is actually the main one that connect must of the application
 *
 * It gets the source of the Database , make sure the file is exist
 * get the context and also get the url at the web server
 *
 * By the way are using Amazon web Service S3 for database
 */

public class PostContextListener implements ServletContextListener {
    static final Logger logger = Logger.getLogger(PostContextListener.class);

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

        PostRepository postRepository = new PostRepository(connection);
        PostService postService = new PostServiceImp(postRepository);

        //set context attributes
        context.setAttribute("postService",postService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}

/**
 * End of this class
 */
