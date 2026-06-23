package com.ideagensys.useradmin.dto;

public class RegisterResponse {

    private boolean success;
    private String message;
    private UserData data;
    private String token;

    public RegisterResponse(boolean success, String message, UserData data, String token) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.token = token;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public UserData getData() {
        return data;
    }

    public String getToken() {
        return token;
    }
}