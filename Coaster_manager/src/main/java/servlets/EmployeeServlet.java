package servlets;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.SQLDatabaseEmployees;
import models.Employee;
import utils.PostgresConnectionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Project 2
 *
 * This servlet is used for API calls involving employees.
 * It implements the doGet, doPost, doPUt, and doDelete methods
 * for DAO interaction.
 *
 * Json responses added with assistnce from Jean Adolf and Paityn Maynard
 *
 * Modifications:
 * Reginald Jefferson   - 5/15/20
 *                      - added missing constructor for doPut so values can be updated.
 *
 * @author Reginald Jefferson
 * @version 05/13/2020
 */
public class EmployeeServlet extends HttpServlet {
    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String json = null;
        SQLDatabaseEmployees employees = new SQLDatabaseEmployees(new PostgresConnectionUtil());

        if (req.getHeader("find").equals("all")) {
            List<Employee> employeeList = employees.findAll();
            Map<String, ArrayList> options = new LinkedHashMap<>();
            options.put("employees", (ArrayList) employeeList);

            json = new Gson().toJson(options);
            resp.setContentType("application/json;charset=UTF-8");
            resp.getWriter().write(json);
        }
        else if (req.getHeader("find").equals("id")) {
            Employee employee = null;
            Map<String, String> options = new LinkedHashMap<>();
            try {
                int id = req.getIntHeader("id");
                employee = employees.findByID(id);

                options.put("id", (String.valueOf(employee.getId())));
                options.put("fName", (String.valueOf(employee.getFname())));
                options.put("lName", (String.valueOf(employee.getLname())));
                options.put("phnNum", (String.valueOf(employee.getPhoneNum())));
                options.put("email", (String.valueOf(employee.getEmail())));

                json = new Gson().toJson(options);
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().write(json);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        String json = null;
        if (data.get("add").getAsString().equals("new")) {
            try {
                String fName = data.get("fName").getAsString();
                String lName = data.get("lName").getAsString();
                String phnNum = data.get("phnNum").getAsString();
                String email = data.get("email").getAsString();
                String pword = data.get("pword").getAsString();
                int bossId = data.get("bossId").getAsInt();
                boolean admin = data.get("admin").getAsBoolean();

                Employee employee = new Employee(fName, lName,phnNum, email, pword, bossId, admin);
                SQLDatabaseEmployees employees = new SQLDatabaseEmployees(new PostgresConnectionUtil());
                employees.add(employee);

                Map<String, String> options = new LinkedHashMap<>();
                options.put("fName", (String.valueOf(employee.getFname())));
                options.put("lName", (String.valueOf(employee.getLname())));
                options.put("phnNum", (String.valueOf(employee.getPhoneNum())));
                options.put("email", (String.valueOf(employee.getEmail())));
                options.put("pword", (String.valueOf(employee.getPword())));
                options.put("bossID", (String.valueOf(employee.getBossid())));
                options.put("admin", (String.valueOf(employee.isAdmin())));

                json = new Gson().toJson(options);
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().write(json);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (data.get("add").getAsString().equals("login")) {
            try {
                Employee loginCheck = new SQLDatabaseEmployees(new PostgresConnectionUtil())
                        .findByID(data.get("id").getAsInt());
                if (loginCheck == null) {
                    Map<String, String> options = new LinkedHashMap<>();
                    options.put("notice", "No account exits. Make a new accoutn please.");
                    json = new Gson().toJson(options);
                    resp.setContentType("application/json;charset=UTF-8");
                    resp.getWriter().write(json);
                }
                else if (loginCheck.getPword().equals(data.get("pword").getAsString())) {
                    Map<String, String> options = new LinkedHashMap<>();
                    options.put("email", loginCheck.getEmail());
                    options.put("adminPriv", (String.valueOf(loginCheck.isAdmin())));
                    json = new Gson().toJson(options);
                    resp.setContentType("application/json;charset=UTF-8");
                    resp.getWriter().write(json);
                }
                else {
                    Map<String, String> options = new LinkedHashMap<>();
                    options.put("message", "The password entered doesn't match our records.");
                    json = new Gson().toJson(options);
                    resp.setContentType("application/json;charset=UTF-8");
                    resp.getWriter().write(json);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Map<String, String> options = new LinkedHashMap<>();
                options.put("message", "The email entered doesn't match our records.");
                json = new Gson().toJson(options);
                resp.setContentType("application/json;charset=UTF-8");
                resp.getWriter().write(json);
            }
        }
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(),JsonObject.class);
        if(data.get("update") == null) {
            String json = null;
            try {
                int id = req.getIntHeader("id");

                String fName = data.get("fName").getAsString();
                String lName = data.get("lName").getAsString();
                String phnNum = data.get("phnNum").getAsString();
                String email = data.get("email").getAsString();
                String pword = data.get("pword").getAsString();
                boolean admin = data.get("admin").getAsBoolean();

                Employee employee = new Employee(fName, lName, phnNum, email, pword, admin);
                SQLDatabaseEmployees employees = new SQLDatabaseEmployees((new PostgresConnectionUtil()));
                employees.update(id, employee);

                Map<String, String> options = new LinkedHashMap<>();
                options.put("fName", (String.valueOf(employee.getFname())));
                options.put("lName", (String.valueOf(employee.getLname())));
                options.put("phnNum", (String.valueOf(employee.getPhoneNum())));
                options.put("email", (String.valueOf(employee.getEmail())));
                options.put("pword", (String.valueOf(employee.getPword())));
                options.put("admin", (String.valueOf(employee.isAdmin())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
        if(!(data.get("remove") == null)) {
            String json = null;
            try {
                int id = data.get("id").getAsInt();

                SQLDatabaseEmployees employees = new SQLDatabaseEmployees(new PostgresConnectionUtil());
//                Employee employee = employees.findByID(id);
                employees.remove(id);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
