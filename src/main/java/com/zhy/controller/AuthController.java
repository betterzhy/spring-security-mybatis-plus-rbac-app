package com.zhy.controller;

import com.zhy.model.dto.ApiResponse;
import com.zhy.model.dto.ModifyPasswordRequest;
import com.zhy.model.dto.SignInRequest;
import com.zhy.model.dto.SignUpRequest;
import com.zhy.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  @author zhy
 */
@Api(tags = "用户认证")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Operation(summary = "用户注册")
    @PostMapping("/signup")
    public ApiResponse<?> signup(@Validated @RequestBody SignUpRequest signUpRequest) {
        return ApiResponse.ok("注册成功",
                authService.signup(signUpRequest)
        );
    }

    @Operation(summary = "用户登录")
    @PostMapping("/signin")
    public ApiResponse<?> signin(@Validated @RequestBody SignInRequest signInRequest) {
        return ApiResponse.ok("登陆成功",
                authService.signin(signInRequest)
        );
    }

    @Operation(summary = "修改密码")
    @PostMapping("/modifyPassword")
    public ApiResponse<?> modifyPassword(Authentication authentication, @Validated @RequestBody ModifyPasswordRequest modifyPasswordRequest) {
        authService.modifyPassword(authentication, modifyPasswordRequest);
        return ApiResponse.ok("密码修改成功，请退出重登");
    }

    @Operation(summary = "退出登录")
    @PostMapping("/logout")
    public ApiResponse<?> logout(Authentication authentication) {
        authService.logout(authentication);
        return ApiResponse.ok("退出成功");
    }
}

