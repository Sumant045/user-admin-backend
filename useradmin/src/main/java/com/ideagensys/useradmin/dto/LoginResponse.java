package com.ideagensys.useradmin.dto;



public class LoginResponse {
    private boolean success;
    private String message;
    private UserData data;   // ← FIXED (was User)
    private String token;

    public LoginResponse(boolean success, String message, UserData data, String token) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.token = token;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public UserData getData() { return data; }
    public String getToken() { return token; }
}