package com.Project0.users;

public class Users
{
    private String username;
    private String privy;
    private String pass;

    public Users(String user,String passw,String privy)
    {
        this.username = user;
        this.pass = passw;
        this.privy = privy;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPrivy()
    {
        return this.privy;
    }

    public void setPrivy(String privy) {
        this.privy = privy;
    }
    public String getPass(){
        return this.pass;
    }

    @Override
    public String toString() {
        return "Name/ID = " + username +
                "   Password = ******";
    }
}
