package com.zhy.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author zhy
 */
public class CaptchaVerifyRequest {
    @NotBlank
    private String captchaId;

    @NotBlank
    @Size(min = 5, max = 5)
    private String captcha;

    public CaptchaVerifyRequest(String captchaId, String captcha) {
        this.captchaId = captchaId;
        this.captcha = captcha;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
}
