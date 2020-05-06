package bank.servlets;

import bank.model.Transaction;
import bank.model.User;
import bank.services.AccountService;
import bank.services.UserServices;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class TransactionServlet extends HttpServlet {
    AccountService accountService;
    UserServices us;
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    /*
    * service -- runs for each and every request made, after the init method
                 has run at least once.
     */
        System.out.println("Servicing MyServlet");
        us = new UserServices();
        accountService = new AccountService();
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

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        JSONObject job=new JSONObject();
        boolean transactionValid = false;
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("userEmail") && us.retrieveUserByEmail(cookies[i].getValue()).getRole().equals("customer")) {
                transactions = accountService.getTransaction(cookies[i].getValue(), Integer.parseInt(req.getParameter("accountID")));
            }
        }
        JSONArray jary = new JSONArray(transactions);
        resp.getWriter().write(jary.toString());
    }
}
