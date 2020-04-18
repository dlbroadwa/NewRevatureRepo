package com.ex.DAO;

/*
* Allow the database information to remain hidden by pulling them from the environment and using getter to make this class reusable
*/

public class GetEnvironmentVar {

//Instant Variables
    private String url = System.getenv("PROJECT_0_URL");
    private String username = System.getenv("PROJECT_0_USERNAME");
    private String password = System.getenv("PROJECT_0_PASSWORD");
    private String schema = System.getenv("PROJECT_0_SCHEMA");

//Constructor
    public GetEnvironmentVar(){}

//Getters
    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSchema() {
        return schema;
    }

}
