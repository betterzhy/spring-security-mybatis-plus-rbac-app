package com.zhy.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author zhy
 */
public class SignInRequest {
    @NotBlank
    @Size(min = 4, max = 36)
    private String username;

    @NotBlank
    @Size(min = 6, max = 18)
    private String password;

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
}
