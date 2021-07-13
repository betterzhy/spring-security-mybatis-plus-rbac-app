package com.zhy.controller;


import com.zhy.model.dto.ApiResponse;
import com.zhy.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhy
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/api")
public class SysUserController {
    @Autowired
    private SysUserService userService;

    @Operation(summary = "获取用户简介")
    @GetMapping("/user/getUserProfile")
    @PreAuthorize("hasAuthority('sys:user:list')")
    public ApiResponse<?> getUserProfile(@RequestParam("id") Long userId) {

        return ApiResponse.ok("成功获取用户信息",
                userService.getUserProfile(userId)
        );
    }

    @Operation(summary = "获取用户菜单列表")
    @GetMapping("/user/listUserMenu")
    @PreAuthorize("hasAuthority('sys:user:menu')")
    public ApiResponse<?> listUserMenu(@RequestParam("id") Long userId) {

        return ApiResponse.ok("成功获取用户信息",
                userService.listUserMenu(userId)
        );
    }

    @Operation(summary = "获取用户角色列表")
    @GetMapping("/user/listUserRole")
    @PreAuthorize("hasAuthority('sys:user:role')")
    public ApiResponse<?> listUserRole(@RequestParam("id") Long userId) {

        return ApiResponse.ok("成功获取用户信息",
                userService.listUserRole(userId)
        );
    }



}
