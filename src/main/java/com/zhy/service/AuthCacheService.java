package com.zhy.service;

import com.zhy.security.CustomUserDetails;

public interface AuthCacheService {
    /**
     * 缓存UserDetails
     */
    void setUserDetails(CustomUserDetails userDetails);

    /**
     * 获取UserDetails
     */
    CustomUserDetails getUserDetails(String username);

    /**
     * 清空UserDetails
     */
    void delUserDetails(String username);
}
