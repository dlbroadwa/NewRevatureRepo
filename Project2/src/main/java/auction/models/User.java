package auction.models;

public class User {

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

    public User(String userName, String password, String creditCardNumber, String role) {
        this.userName = userName;
        this.password = password;
        this.creditCardNumber = creditCardNumber;
        this.role = role;
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