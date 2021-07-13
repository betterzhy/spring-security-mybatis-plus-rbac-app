package com.zhy.util;

/**
 * @author zhy
 */
public interface AppConstants {
    // 验证码
    String CAPTCHA_REDIS_KEY_PREFIX = "APP:CAPTCHA";
    // 用户信息
    String REDIS_KEY_PREFIX_USER = "APP:USER";

    // 默认页
    String DEFAULT_PAGE_NUMBER = "1";
    // 每页条数
    String DEFAULT_PAGE_SIZE = "10";
}
