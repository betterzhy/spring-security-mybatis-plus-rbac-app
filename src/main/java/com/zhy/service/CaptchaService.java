package com.zhy.service;

import com.zhy.model.dto.CaptchaGenerateResponse;
import com.zhy.model.dto.CaptchaVerifyRequest;

public interface CaptchaService {
    /**
     * 生成验证码
     */
    CaptchaGenerateResponse generate();

    /**
     * 验证验证码
     */
    void verify(CaptchaVerifyRequest verifyRequest);
}
