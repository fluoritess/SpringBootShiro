package com.shan.springbootshirotest.pojo;

public class user {
    private Integer id;

    private String username;

    private String userpassword;

    private Integer permissions;

    private String email;

    public user(Integer id, String username, String userpassword, Integer permissions, String email) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.permissions = permissions;
        this.email = email;
    }

    public user(Integer id, String username, String userpassword, Integer permissions) {
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
        this.permissions = permissions;
    }

    public user() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword == null ? null : userpassword.trim();
    }

    public Integer getPermissions() {
        return permissions;
    }

    public void setPermissions(Integer permissions) {
        this.permissions = permissions;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}