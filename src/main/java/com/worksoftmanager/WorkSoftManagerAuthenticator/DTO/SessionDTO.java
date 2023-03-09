package com.worksoftmanager.WorkSoftManagerAuthenticator.DTO;

import java.sql.Timestamp;

public class SessionDTO {

    private String username;
    private String token;
    private Timestamp timestamp;



    public SessionDTO(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
