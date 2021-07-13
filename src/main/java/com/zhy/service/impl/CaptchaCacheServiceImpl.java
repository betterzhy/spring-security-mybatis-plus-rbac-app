package com.zhy.service.impl;

import com.zhy.util.AppConstants;
import com.zhy.service.CaptchaCacheService;
import com.zhy.util.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zhy
 */
@Service
public class CaptchaCacheServiceImpl implements CaptchaCacheService {
    @Autowired
    private RedisHelper redisHelper;

    @Value("${app.security.captcha.expiration}")
    private long expiration;

    @Override
    public void set(String captchaId, String captcha) {
        String key = AppConstants.CAPTCHA_REDIS_KEY_PREFIX + ":" + captchaId;
        redisHelper.set(key, captcha, expiration);
    }

    @Override
    public String get(String captchaId) {
        String key = AppConstants.CAPTCHA_REDIS_KEY_PREFIX + ":" + captchaId;
        return (String) redisHelper.get(key);
    }

    @Override
    public void del(String captchaId) {
        String key = AppConstants.CAPTCHA_REDIS_KEY_PREFIX + ":" + captchaId;
        redisHelper.del(key);
    }
}
