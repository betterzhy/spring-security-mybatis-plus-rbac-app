package com.zhy.service;

import com.zhy.model.dto.*;
import org.springframework.security.core.Authentication;

public interface AuthService {
    /**
     * 注册
     */
    SignUpResponse signup(SignUpRequest signUpRequest);

    /**
     * 登录
     */
    SignInResponse signin(SignInRequest signInRequest);

    /**
     * 密码修改
     */
    void modifyPassword(Authentication authentication, ModifyPasswordRequest modifyPasswordRequest);

    /**
     * 登出
     * 1、清空SecurityContext
     * 2、清空用户信息缓存
     */
    void logout(Authentication authentication);
}
