package com.zhy.service;

import com.zhy.model.dto.PagedResponse;
import com.zhy.model.dto.UserProfile;
import com.zhy.model.entity.SysPerm;
import com.zhy.model.entity.SysRole;

import java.util.List;

public interface SysUserService {
    UserProfile getUserProfile(Long userId);

    List<SysRole> listUserRole(Long userId);

    List<SysPerm> listUserMenu(Long userId);

    PagedResponse<SysRole> pageUserRole(Long userId, Long pageNum, Long pageSize);

    PagedResponse<SysPerm> pageUserMenu(Long userId, Long pageNum, Long pageSize);
}
