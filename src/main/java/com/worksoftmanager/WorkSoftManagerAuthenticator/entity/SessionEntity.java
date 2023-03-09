package com.worksoftmanager.WorkSoftManagerAuthenticator.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sessions")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "token")
    private String token;

    @Column(name = "created_at")
    private Timestamp timestamp;

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public SessionEntity(String username, String token) {
        this.username = username;
        this.token = token;
    }

    public SessionEntity() {
    }

    public Long getId() {
        return id;
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
}