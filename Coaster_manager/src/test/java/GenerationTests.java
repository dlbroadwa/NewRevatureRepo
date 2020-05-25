///**
// * Initialized by Jean Aldoph II, This testing set will be
// * used to MOCK generate employees for the Management Service of the
// * theme park. These tests will only determine the functionality
// * of the tests, they DO NOT need to be called during service.
// */
//
////
//
//import com.google.gson.Gson;
//import data.GenerationDAO;
//import org.apache.commons.httpclient.*;
//import org.apache.commons.httpclient.methods.*;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
//import org.junit.Test;
//import java.time.LocalDateTime;
//
//import java.io.*;
//
//
//import org.apache.commons.httpclient.params.HttpMethodParams;
//
//public class GenerationTests {
////
////    ScriptEngineManager manager = new ScriptEngineManager();
////    ScriptEngine engine = manager.getEngineByName("JavaScript");
////    // read script file
////    engine.eval(Files.newBufferedReader(Paths.get("/EmployeeGeneration.js"), StandardCharsets.UTF_8)));
////
////    Invocable inv = (Invocable) engine;
//// //call function from script file
//// inv.invokeFunction("yourFunction", "param");
//
//    @Test
//    public void testGen()
//    {
//
//        GenerationDAO genDao = new GenerationDAO();
//        try
//        {
//            genDao.makeAday(1);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void testNewAPImeans() throws IOException {
////        URL currenturl = new URL("http://172.17.199.5:31515/TicketServlet");
////        HttpURLConnection con = (HttpURLConnection)currenturl.openConnection();
////        con.setRequestMethod("POST");
////        con.setRequestProperty("Content-Type", "application/json; utf-8");
////        con.setRequestProperty("Accept","application/json");
////        con.setDoOutput(true);
////        String jsoninput = "{\"number\":\"1\",\"cusotmerID\":\"42\"}";
////
////        try (OutputStream os = con.getOutputStream())
////        {
////            byte[] input = jsoninput.getBytes("utf-8");
////            System.out.println(input[0]);
////            os.write(input,0,input.length);
////        }
////        try(BufferedReader br = new BufferedReader(
////            new InputStreamReader(con.getInputStream(), "utf-8"))) {
////            StringBuilder response = new StringBuilder();
////            String responseLine = null;
////            while ((responseLine = br.readLine()) != null) {
////                response.append(responseLine.trim());
////            }
////            System.out.println(response.toString());
////        }
//
//        String postUrl = "http://172.17.199.5:31515/TicketServlet";// put in your url
//        Gson gson = new Gson();
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//        HttpPost post  = new HttpPost(postUrl);
//        StringEntity postingString = new StringEntity(gson.toJson("{\"number\":\"1\",\"cusotmerID\":\"42\"}"));//gson.tojson() converts your pojo to json
//        post.setEntity(postingString);
//        post.setHeader("Content-type", "application/json");
//        HttpResponse response = httpClient.execute(post);
//        System.out.println(response.getStatusLine().toString());
//
////        String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
////        URL url = new URL ("https://reqres.in/api/users");
////        HttpURLConnection con = (HttpURLConnection)url.openConnection();
////        con.setRequestMethod("POST");
////        con.setRequestProperty("Content-Type", "application/json; utf-8");
////        con.setRequestProperty("Accept", "application/json");
////        con.setDoOutput(true);
////
////        try(OutputStream os = con.getOutputStream()) {
////            byte[] input = jsonInputString.getBytes("utf-8");
////            os.write(input, 0, input.length);
////        }
////
////        try(BufferedReader br = new BufferedReader(
////                new InputStreamReader(con.getInputStream(), "utf-8"))) {
////            StringBuilder response = new StringBuilder();
////            String responseLine = null;
////            while ((responseLine = br.readLine()) != null) {
////                response.append(responseLine.trim());
////            }
////            System.out.println(response.toString());
////        }
////
//
////        Map<String, String> params= new LinkedHashMap<>();
////        params.put("number",String.valueOf(1));
////        params.put("customerID","42");
////        StringBuilder postData = new StringBuilder();
////        for (Map.Entry<String,String> param : params.entrySet()) {
////            if (postData.length() != 0) postData.append('&');
////            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
////            postData.append('=');
////            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
////        }
////        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
////
////        HttpURLConnection conn = (HttpURLConnection)currenturl.openConnection();
////        conn.setRequestMethod("POST");
////        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
////        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
////        conn.setDoOutput(true);
////        conn.getOutputStream().write(postDataBytes);
////
////        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
////
////        for (int c; (c = in.read()) >= 0;)
////            System.out.print((char)c);
////
////
//
////        //method.addParameter();
////
////        //method.addParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler(2, false));
////        System.out.println(method.getParameters().toString());
////        try {
////            int statusCode = client.executeMethod(method);
////            if (statusCode != HttpStatus.SC_OK)
////            {
////                System.err.println("Method failed: " + method.getStatusLine());
////            }
////            String responseBody = method.getResponseBodyAsString();
////            System.out.println(responseBody);
////
////        }
////        catch (HttpException e)
////        {
////            System.err.println("Fatal protocol violation: " + e.getMessage());
////            e.printStackTrace();
////        }
////        catch (IOException e)
////        {
////            System.err.println("Fatal transport error: " + e.getMessage());
////            e.printStackTrace();
////        }
////        finally
////        {
////            method.releaseConnection();
////        }
//
//
//
//
//
//
////        HttpPost post = new HttpPost("");
////        NameValuePair[] data = {new NameValuePair("days","1")};
////        post.
//    }
//
////    Article Body
////
////    Before implementing a call, make sure you have the URL for the API call, a valid API Key for the environment you are connecting to,
////    and any required data elements. In this example, it is requesting for details of an order by the Transaction ID.
////
////        Import a HTTP Client.
////        Assign the URL and get params to the URL string.
////        Setup the http client.
////        Put it in GET mode.
////        Assign the URL and parameters.
////        Make the request.
////        Process the Response.
////        Close the connection.
////
////
//    @Test
//    public void testCall()
//    {
//        // Fake example transaction ID: 3YC00XQKNVMZ
//        String url = "https://randomuser.me/api/";
//
//        // Create an instance of HttpClient.
//        HttpClient client = new HttpClient();
//
//        // Create a method instance.
//        GetMethod method = new GetMethod(url);
//
//        // Provide custom retry handler is necessary
//        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
//                new DefaultHttpMethodRetryHandler(3, false));
//
//        try
//        {
//            // Execute the method.
//            int statusCode = client.executeMethod(method);
//
//            if (statusCode != HttpStatus.SC_OK)
//            {
//                System.err.println("Method failed: " + method.getStatusLine());
//            }
//
//
//            // Read the response body.
//            byte[] responseBody = method.getResponseBody();
//            String rezzy = new String(responseBody);
//            String[] stack = rezzy.split("\".\"");
//            int holder = 1;
//            String item = "";
//            String item1 = "";
//            String item2 = "";
//            for (String i : stack)
//            {
//                if (i.trim().equals("first"))
//                {
//                    item = (stack[holder]).toString();
//                }
//                else if (i.trim().contains("email"))
//                {
//                    item1 =(stack[holder]).toString();
//                }
//                else if (i.trim().contains("last"))
//                {
//                    item2 = (stack[holder]).split("\"")[0];
//                }
//                //System.out.println(holder-1 +"   "+i);
//                holder++;
//
//            }
//            if (item2 == "") item2 ="Smith";
//            if (item1 == "") item1 = "John";
//            System.out.println("first Name: " + item);
//            System.out.println("Last Name:  "+item2);
//            System.out.println("email:  "+item1);
//
//
//        } catch (HttpException e) {
//            System.err.println("Fatal protocol violation: " + e.getMessage());
//            e.printStackTrace();
//        } catch (IOException e) {
//            System.err.println("Fatal transport error: " + e.getMessage());
//            e.printStackTrace();
//        } finally {
//            // Release the connection.
//            method.releaseConnection();
//        }
//    }
//
//    @Test
//    public void makeCustomers()
//    {
//        int i = 0;
//        while (i++ < 500) new GenerationDAO().makeCustomer();
//    }
//
//    @Test
//    public void makeEmployees()
//    {
//        int i = 0;
//        while (i++ < 500) new GenerationDAO().makeEmployee();
//    }
//
//    @Test
//    public void ldtPare()
//    {
//        LocalDateTime ldt;
//
//    }
//
//}
//
//
//
//
//
//
