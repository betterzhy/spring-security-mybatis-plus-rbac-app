package com.zhy.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhy.exception.ApiException;
import com.zhy.mapper.SysUserMapper;
import com.zhy.model.dto.PagedResponse;
import com.zhy.model.dto.UserProfile;
import com.zhy.model.entity.SysPerm;
import com.zhy.model.entity.SysRole;
import com.zhy.model.entity.SysUser;
import com.zhy.service.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhy
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Override
    public UserProfile getUserProfile(Long userId) {
        SysUser user = userMapper.selectById(userId);
        if (user == null) {
            throw new ApiException("用户不存在");
        }
        UserProfile userProfile = new UserProfile();
        BeanUtils.copyProperties(user, userProfile);
        return userProfile;
    }

    @Override
    public List<SysRole> listUserRole(Long userId) {
        SysUser user = userMapper.selectById(userId);
        if (user == null) {
            throw new ApiException("用户不存在");
        }
        return userMapper.selectRoleById(userId);
    }

    @Override
    public List<SysPerm> listUserMenu(Long userId) {
        SysUser user = userMapper.selectById(userId);
        if (user == null) {
            throw new ApiException("用户不存在");
        }
        return userMapper.selectMenuById(userId);
    }

    @Override
    public PagedResponse<SysRole> pageUserRole(Long userId, Long pageNum, Long pageSize) {
        SysUser user = userMapper.selectById(userId);
        if (user == null) {
            throw new ApiException("用户不存在");
        }
        IPage<SysRole> pageInfo = userMapper.selectRoleById(new Page<>(pageNum, pageSize), userId);
        return new PagedResponse<SysRole>(pageInfo.getRecords(), pageInfo.getCurrent(),
                pageInfo.getSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getCurrent() == pageInfo.getPages()
        );
    }

    @Override
    public PagedResponse<SysPerm> pageUserMenu(Long userId, Long pageNum, Long pageSize) {
        SysUser user = userMapper.selectById(userId);
        if (user == null) {
            throw new ApiException("用户不存在");
        }
        IPage<SysPerm> pageInfo = userMapper.selectMenuById(new Page<>(pageNum, pageSize), userId);
        return new PagedResponse<SysPerm>(pageInfo.getRecords(), pageInfo.getCurrent(),
                pageInfo.getSize(), pageInfo.getTotal(), pageInfo.getPages(), pageInfo.getCurrent() == pageInfo.getPages()
        );
    }

}
