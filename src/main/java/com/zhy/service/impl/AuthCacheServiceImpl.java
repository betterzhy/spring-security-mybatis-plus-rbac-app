package com.zhy.service.impl;

import com.zhy.util.AppConstants;
import com.zhy.security.CustomUserDetails;
import com.zhy.security.UserDetailsCacheDto;
import com.zhy.service.AuthCacheService;
import com.zhy.util.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zhy
 */
@Service
public class AuthCacheServiceImpl implements AuthCacheService {
    @Autowired
    private RedisHelper redisHelper;

    @Value("${app.security.auth.expiration}")
    private long expiration;


    @Override
    public void setUserDetails(CustomUserDetails userDetails) {
        String key = AppConstants.REDIS_KEY_PREFIX_USER + ":" + userDetails.getUsername();
        UserDetailsCacheDto cacheDto = UserDetailsCacheDto.create(userDetails);
        redisHelper.set(key, cacheDto, expiration);
    }

    @Override
    public CustomUserDetails getUserDetails(String username) {
        String key = AppConstants.REDIS_KEY_PREFIX_USER + ":" + username;
        UserDetailsCacheDto cacheDto = (UserDetailsCacheDto) redisHelper.get(key);
        if (cacheDto == null){
            return null;
        }
        return UserDetailsCacheDto.resume(cacheDto);
    }

    @Override
    public void delUserDetails(String username) {
        String key = AppConstants.REDIS_KEY_PREFIX_USER + ":" + username;
        redisHelper.del(key);
    }
}
