package com.zhy.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author zhy
 */
public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 18)
    private String username;

    @NotBlank
    @Size(min = 6, max = 18)
    private String password;

    private String avatar;

    @NotBlank
    @Email
    @Size(min = 6, max = 36)
    private String email;

    private String city;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
