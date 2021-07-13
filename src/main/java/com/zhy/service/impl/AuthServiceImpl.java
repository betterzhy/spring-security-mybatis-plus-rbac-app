package com.zhy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhy.exception.ApiException;
import com.zhy.mapper.SysUserMapper;
import com.zhy.model.dto.*;
import com.zhy.model.entity.SysUser;
import com.zhy.security.CustomUserDetails;
import com.zhy.security.CustomUserDetailsService;
import com.zhy.security.JwtTokenProvider;
import com.zhy.service.AuthCacheService;
import com.zhy.service.AuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author zhy
 */
@Service
public class AuthServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements AuthService {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthCacheService cacheService;

    @Override
    public SignUpResponse signup(SignUpRequest signUpRequest) {
        if (userMapper.selectOne(new QueryWrapper<SysUser>().eq("username", signUpRequest.getUsername())) != null) {
            throw new ApiException("用户名已存在");
        }
        if (userMapper.selectOne(new QueryWrapper<SysUser>().eq("email", signUpRequest.getUsername())) != null) {
            throw new ApiException("邮箱已被使用");
        }

        SysUser user = new SysUser();
        BeanUtils.copyProperties(signUpRequest, user);
        user.setStatus(1);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userMapper.insert(user);
        SignUpResponse signUpResponse = new SignUpResponse();
        BeanUtils.copyProperties(user, signUpResponse);
        return signUpResponse;
    }

    /**
     * 1、authenticationManager做用户校验
     * 2、设置当前认证对象
     * 3、缓存用户信息，方便后续jwt鉴权
     *
     * @return
     */
    @Override
    public SignInResponse signin(SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),
                        signInRequest.getPassword()
                )
        );
        // 更新lastLogin字段
        SysUser user = userMapper.selectOne(new QueryWrapper<SysUser>().eq("username", signInRequest.getUsername()));
        user.setLastLogin(LocalDateTime.now());
        userMapper.updateById(user);
        // 添加到SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        // 缓存用户信息
        cacheService.setUserDetails(userDetails);
        return new SignInResponse(
                jwtTokenProvider.generateToken(signInRequest.getUsername())
        );
    }

    /**
     * 1、更新用户密码
     * 2、更新缓存信息
     */
    @Override
    public void modifyPassword(Authentication authentication, ModifyPasswordRequest modifyPasswordRequest) {
        if (!modifyPasswordRequest.getNewPassword().equals(modifyPasswordRequest.getCheckPassword())) {
            throw new ApiException("输入密码不一致");
        }

        String username = authentication.getName();
        SysUser user = userMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));
        if (user == null) {
            throw new ApiException("用户不存在");
        }
        if (!passwordEncoder.matches(modifyPasswordRequest.getCurrentPassword(), user.getPassword())) {
            throw new ApiException("密码错误");
        }

        String newPassword = passwordEncoder.encode(modifyPasswordRequest.getNewPassword());
        user.setPassword(newPassword);
        userMapper.updateById(user);

        // 更新缓存
        CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        userDetails.setPassword(newPassword);
        cacheService.setUserDetails(userDetails);
    }

    @Override
    public void logout(Authentication authentication) {
        SecurityContextHolder.clearContext();
        cacheService.delUserDetails(authentication.getName());
    }
}
