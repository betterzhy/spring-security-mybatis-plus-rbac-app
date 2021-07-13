package com.zhy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhy.exception.ApiException;
import com.zhy.mapper.SysPermMapper;
import com.zhy.mapper.SysRolePermMapper;
import com.zhy.model.dto.SysMenuSaveRequest;
import com.zhy.model.entity.SysPerm;
import com.zhy.model.entity.SysRolePerm;
import com.zhy.service.SysPermService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhy
 */
@Service
public class SysPermServiceImpl implements SysPermService {
    @Autowired
    private SysPermMapper menuMapper;

    @Autowired
    private SysRolePermMapper roleMenuMapper;


    @Override
    public List<SysPerm> tree() {
        List<SysPerm> menus = menuMapper.selectList(new QueryWrapper<SysPerm>().orderByAsc("order_num"));
        // 返回嵌套结构
        buildTreeMenu(menus);

        return buildTreeMenu(menus);
    }

    @Override
    public SysPerm save(SysMenuSaveRequest menuSaveRequest) {
        SysPerm menu = new SysPerm();
        BeanUtils.copyProperties(menuSaveRequest,menu);
        menu.setStatus(1);
        menuMapper.insert(menu);
        return menu;
    }

    @Override
    public void update(SysPerm sysPerm) {
        menuMapper.updateById(sysPerm);

        // 清除所有与该菜单相关的权限缓存
//        sysUserService.clearUserAuthorityInfoByMenuId(sysPerm.getId());
    }

    @Override
    public void delete(Long menuId) {
        int count = menuMapper.selectCount(new QueryWrapper<SysPerm>().eq("parent_id", menuId));
        if (count > 0) {
            throw new ApiException("请先删除子菜单");
        }
        // 清除所有与该菜单相关的权限缓存
//        menuService.clearUserAuthorityInfoByMenuId(id);
        menuMapper.deleteById(menuId);
        // 同步删除中间关联表
        roleMenuMapper.delete(new QueryWrapper<SysRolePerm>().eq("menu_id", menuId));
    }

    private List<SysPerm> buildTreeMenu(List<SysPerm> menus) {
        List<SysPerm> finalMenus = new ArrayList<>();
/*
        // 先各自寻找到各自的孩子
        for (SysPerm menu : menus) {

            for (SysPerm e : menus) {
                if (menu.getId().equals(e.getParentId())) {
                    menu.getChildren().add(e);
                }
            }

            // 提取出父节点
            if (menu.getParentId() == 0L) {
                finalMenus.add(menu);
            }
        }*/

        return finalMenus;
    }
}
