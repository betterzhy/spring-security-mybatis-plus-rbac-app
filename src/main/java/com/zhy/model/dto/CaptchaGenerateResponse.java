package com.zhy.model.dto;

/**
 * @author zhy
 */
public class CaptchaGenerateResponse {
    private String captchaKey;
    private String base64Image;

    public CaptchaGenerateResponse(String captchaKey, String base64Image) {
        this.captchaKey = captchaKey;
        this.base64Image = base64Image;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}
