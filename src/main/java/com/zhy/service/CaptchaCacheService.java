package com.zhy.service;

public interface CaptchaCacheService {
    /**
     * 缓存验证码
     */
    void set(String captchaId, String captcha);

    /**
     * 获取验证码
     */
    String get(String captchaId);

    /**
     * 清空验证码
     */
    void del(String captchaId);
}
