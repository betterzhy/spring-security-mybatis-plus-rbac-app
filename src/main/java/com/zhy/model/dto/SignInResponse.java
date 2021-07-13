package com.zhy.model.dto;

/**
 * @author zhy
 */
public class SignInResponse {
    private String tokenType = "Bearer";
    private String token;

    public SignInResponse(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
