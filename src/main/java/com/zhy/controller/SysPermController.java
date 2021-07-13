package com.zhy.controller;

import com.zhy.model.dto.ApiResponse;
import com.zhy.model.dto.SysMenuSaveRequest;
import com.zhy.model.entity.SysPerm;
import com.zhy.service.SysPermService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author zhy
 */
@Api(tags = "菜单管理")
@RestController
@RequestMapping("/api/menu")
public class SysPermController {

    @Autowired
    private SysPermService menuService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('sys:menu:list')")
    public ApiResponse<?> list() {
        return ApiResponse.ok("获取菜单列表成功",
                menuService.tree()
        );
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('sys:menu:save')")
    public ApiResponse<?> save(@Validated @RequestBody SysMenuSaveRequest menuSaveRequest) {
        return ApiResponse.ok("新增菜单成功",
                menuService.save(menuSaveRequest)
        );
    }

    @PostMapping("/update")
    @PreAuthorize("hasAuthority('sys:menu:update')")
    public ApiResponse<?> update(@Validated @RequestBody SysPerm sysPerm) {
        menuService.update(sysPerm);
        return ApiResponse.ok("菜单信息更新成功");
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    public ApiResponse<?> delete(@PathVariable("id") Long menuId) {
        menuService.delete(menuId);
        return ApiResponse.ok("菜单删除成功");
    }
}
