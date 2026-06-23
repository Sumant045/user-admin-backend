package com.ideagensys.useradmin.dto;

public class UserData {
    private Long id;
    private String fullName;   // ← ADDED
    private String username;
    private String email;

    public UserData(Long id, String fullName, String username, String email) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
}