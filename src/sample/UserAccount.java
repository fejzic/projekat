package sample;

public class UserAccount {
    private String firstName;
    private String LastName;
    private String userName;
    private String password;

    public UserAccount(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        LastName = lastName;
        this.userName = userName;
        this.password = password;
    }

    public UserAccount() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
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
}
