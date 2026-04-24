package model;

import java.time.LocalDateTime;


public class User {
    private int userId;
    private int employeeId;
    private int roleId;

    private String username;
    private String passwordHash;
    private boolean isActive;
    
    private LocalDateTime createdAt;
    private LocalDateTime lastLogin;

    public User(int userId, int employeeId, int roleId, String username, String passwordHash, boolean isActive, LocalDateTime createdAt, LocalDateTime lastlogin) {
        this.userId = userId;
        this.employeeId = employeeId;
        this.roleId = roleId;
        this.username = username;
        this.passwordHash = passwordHash;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.lastLogin = lastlogin;
    }

    public User(int employeeId, int roleId, String userName, String passwordHash)
    {
        this.employeeId = employeeId;
        this.roleId = roleId;
        this.username = userName;
        this.passwordHash = passwordHash;
    }

    public int getUserId(){
        return userId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getRoleId() {
        return roleId;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

}