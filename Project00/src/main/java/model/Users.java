package model;

public class Users {

    private String name;
    private String phone_number;
    private String email_address;
    private String user_pin;

    /**
     * Setting default user with default values for all class members.
     */
    public Users(){
        this.name = "default";
        this.phone_number = "default";
        this.email_address = "default";
        this.user_pin = "default";
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
}
