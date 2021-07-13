package com.zhy.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author zhy
 */
public class ModifyPasswordRequest {
    @NotBlank
    @Size(min = 6,max = 18)
    private String currentPassword;

    @NotBlank
    @Size(min = 6,max = 18)
    private String newPassword;

    @NotBlank
    @Size(min = 6,max = 18)
    private String checkPassword;

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }
}
