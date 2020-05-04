package com.game.system;

import com.game.data.AccountSQLRepo;
import com.game.data.ItemSQLRepo;
import com.game.data.MessageSQLRepo;
import com.game.data.Repository;
import com.game.models.Account;
import com.game.service.accountservices.AccountDetailService;
import com.game.service.accountservices.AccountDetailServiceImp;
import com.game.service.itemservices.ItemService;
import com.game.service.itemservices.ItemServiceImp;
import com.game.service.messageservices.MessageService;
import com.game.service.messageservices.MessageServiceImp;
import com.game.utils.ConnectionUtils;
import com.game.utils.PostgresConnectionUtil;
import org.apache.log4j.Logger;
import org.postgresql.Driver;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PortalContextListener  implements ServletContextListener {
    static final Logger logger = Logger.getLogger(Account.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
        try {
            prop.load(inputStream);
        } catch (IOException e) {
            logger.debug("db.properties not found");
        }
        ConnectionUtils connection = new PostgresConnectionUtil(prop.getProperty("url"),
                prop.getProperty("username"),prop.getProperty("password"));

        Repository<Account,String> accountSQLRepo = new AccountSQLRepo(connection);
        ItemSQLRepo itemSQLRepo = new ItemSQLRepo(connection);
        MessageSQLRepo messageSQLRepo = new MessageSQLRepo(connection);
        AccountDetailService accountDetailService = new AccountDetailServiceImp(accountSQLRepo);
        ItemService itemService = new ItemServiceImp(itemSQLRepo);
        MessageService messageService = new MessageServiceImp(messageSQLRepo);

        context.setAttribute("itemService", itemService);
        context.setAttribute("accountDetailService",accountDetailService);
        context.setAttribute("messageService", messageService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
