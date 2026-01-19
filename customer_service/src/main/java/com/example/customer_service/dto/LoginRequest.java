package com.example.customer_service.dto;

public class LoginRequest {
    private String ssnId;
    private String password;

    // Getters and Setters
    public String getSsnId() {
        return ssnId;
    }

    public void setSsnId(String ssnId) {
        this.ssnId = ssnId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
