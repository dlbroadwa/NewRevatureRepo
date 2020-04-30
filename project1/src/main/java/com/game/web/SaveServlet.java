package com.game.web;

import com.game.data.AccountSQLRepo;
import com.game.models.Account;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.game.service.accountservices.AccountDetailService;
import com.game.service.accountservices.CreationService;
import com.game.service.accountservices.CreationServiceImp;
import com.game.system.AuthenticationStatus;
import com.
import com.game.service.accountservices.AccountDetailServiceImp;
import com.game.utils.ConnectionUtils;
import com.game.utils.PostgresConnectionUtil;

public class SaveServlet extends HttpServlet {
    AccountDetailService newAccount;
    AccountSQLRepo aRepo;
    CreationService creationService;
    ConnectionUtils connectionUtils = new PostgresConnectionUtil();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    * service -- runs for each and every request made, after the init method
                 has run at least once.
     */
        aRepo = new AccountSQLRepo(connectionUtils);
        newAccount = new AccountDetailServiceImp(aRepo);
        creationService = new CreationServiceImp(newAccount);

        System.out.println("Servicing MyServlet");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        /*
         * destroy -- gets called when the server needs it to be.
         *           most likely at server shutdown.
         * */
        System.out.println("Destroy MyServlet");
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        /*
         * init -- beginning of the servlet lifecycle
         *         it runs once, if the servlet has never been initialize
         *         when the first request to a matching url pattern is made.
         *         You can preload servlet with <load-on-startup> in the web.xml.
         * */
        System.out.println("Init MyServlet");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        String name = req.getParameter("myname");

        if(name != null) {
            resp.getWriter().write("Hello, " + name);
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("Hello, World!");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
/*        String name = req.getParameter("myname");

        if(name != null) {
            resp.getWriter().write("Hello, " + name);
            resp.setStatus(201);
            resp.setContentType("text/plain");
        } else {
            resp.getWriter().write("Hello, World!");
            resp.setStatus(201);
            resp.setContentType("text/plain");
        }*/


        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        creationService.signUp(username, password, email);

    }
}
