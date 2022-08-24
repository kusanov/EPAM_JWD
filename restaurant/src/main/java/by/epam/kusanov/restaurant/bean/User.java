package by.epam.kusanov.restaurant.bean;

import java.io.Serializable;

public class User implements Serializable {

    private int userId;
    private String login;
    private String password;
    private Role role;
    private boolean isActive;


    public User(int userId, String login, String password, Role role, boolean isActive) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }
         public User(int userId, String login, Role role, boolean isActive) {
            this.userId = userId;
            this.login = login;
            this.role = role;
            this.isActive = isActive;
    }

    public User() {

    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role.getRoleName() +
                ", isActive=" + isActive +
                '}';
    }
}
