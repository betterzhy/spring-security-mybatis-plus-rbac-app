package com.zhy.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.lang.UUID;
import com.zhy.exception.ApiException;
import com.zhy.model.dto.CaptchaGenerateResponse;
import com.zhy.model.dto.CaptchaVerifyRequest;
import com.zhy.service.CaptchaCacheService;
import com.zhy.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhy
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {
    @Autowired
    private CaptchaCacheService cacheService;

    @Override
    public CaptchaGenerateResponse generate() {
        String id = UUID.randomUUID().toString();
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 40, 5, 1);
        cacheService.set(id, lineCaptcha.getCode());
        return new CaptchaGenerateResponse(id, lineCaptcha.getImageBase64Data());
    }

    @Override
    public void verify(CaptchaVerifyRequest verifyRequest) {
        String id = verifyRequest.getCaptchaId();
        String code = cacheService.get(id);
        if (code == null) {
            throw new ApiException("验证码不存在或已过期");
        }
        if (verifyRequest.getCaptcha().equals(code)) {
            cacheService.del(id);
        } else {
            throw new ApiException("验证码错误");
        }
    }
}
