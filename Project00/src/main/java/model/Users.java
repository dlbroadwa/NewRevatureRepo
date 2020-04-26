package model;

/**
 * Class desciption: This class is the bank user model
 * email address will be used as user ID and its corresponding column will be unique column in the database
 * pin number will be used to authenticate the user before entering the ATM menu section
 */
public class Users {

    private String name;
    private String phone_number;
    private String email_address;
    private String user_pin;



    private int userID;



    private boolean is_admin;

    /**
     * Setting default user with default values for all class members.
     */
    public Users(){
        this.name = "default";
        this.phone_number = "default";
        this.email_address = "default";
        this.user_pin = "default";
        this.is_admin = false;
        this.userID = -1;
    }

    /**
     * Retrieving User Name
     * @return name member
     */
    public String getName() {
        return name;
    }

    /**
     * Setting the UserName object.
     * @param user_name is retrieved from database table myschema.users3
     */
    public void setName(String user_name) {
        this.name = user_name;
    }

    /**
     * Retrieving Phone Number
     * @return phone_number member
     */
    public String getPhone_number() {
        return phone_number;
    }
    /**
     * Setting the phone_number member.
     * @param phone_number is retrieved from database table myschema.users3
     */
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * Retrieving email_address class member
     * @return email_address member
     */
    public String getEmail_address() {
        return email_address;
    }
    /**
     * Setting the email_address member.
     * @param email_address is retrieved from database table myschema.users3
     */
    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }
    /**
     * Retrieving user_pin class member
     * @return user_pin member
     */
    public String getUser_pin() {
        return user_pin;
    }
    /**
     * Setting the user_pin member.
     * @param user_pin is retrieved from database table myschema.users3
     */
    public void setUser_pin(String user_pin) {
        this.user_pin = user_pin;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
