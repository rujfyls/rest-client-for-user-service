package ru.feduncov.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.time.LocalDateTime;

public class User implements Serializable {

    @JsonProperty("id")
    private Long userId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("e_mail")
    private String email;

    @JsonProperty("reg_time")
    private LocalDateTime regTime;
    @JsonProperty("role")
    private String role;

    public User(Long userId, String username, String email, LocalDateTime regTime, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.regTime = regTime;
        this.role = role;
    }

    public User() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getRegTime() {
        return regTime;
    }

    public void setRegTime(LocalDateTime regTime) {
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", regTime=" + regTime +
                ", role='" + role + '\'' +
                '}';
    }
}
