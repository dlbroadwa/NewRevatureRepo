package auction.models;

public class User {

    private int userId;
    private String userName;
    private String password;
    private String creditCardNumber;
    private String role;


    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
        this.creditCardNumber = null;
        this.role = null;
    }

    public User(int id, String userName, String password, String creditCardNumber, String role) {
        this.userId = id;
        this.userName = userName;
        this.password = password;
        this.creditCardNumber = creditCardNumber;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof User)) return false;
        if (!super.equals(object)) return false;

        User user = (User) object;

        if (!userName.equals(user.userName)) return false;
        if (!password.equals(user.password)) return false;
        if (creditCardNumber != null ? !creditCardNumber.equals(user.creditCardNumber) : user.creditCardNumber != null)
            return false;
        return role != null ? role.equals(user.role) : user.role == null;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + userName.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + (creditCardNumber != null ? creditCardNumber.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}