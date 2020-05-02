package models;

import org.codehaus.plexus.i18n.I18NTokenizer;

public class Users
{
    private String f_name;
    private String l_Name;
    private String email;
    private String password;
    private Integer phone;
    private Boolean admin;

    public Users(String f_Name, String l_Name, String email, String password,
                 Integer phone, Boolean admin)
    {
        this.f_name = f_Name;
        this.l_Name = l_Name;
        this. email = email;
        this.password = password;
        this.phone = phone;
        this.admin = admin;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_Name() {
        return l_Name;
    }

    public void setL_Name(String l_Name) {
        this.l_Name = l_Name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
