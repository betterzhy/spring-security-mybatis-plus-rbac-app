package com.zhy.service;

import com.zhy.model.dto.SysMenuSaveRequest;
import com.zhy.model.entity.SysPerm;

import java.util.List;

public interface SysPermService {
    List<SysPerm> tree();

    SysPerm save(SysMenuSaveRequest sysMenu);

    void update(SysPerm sysPerm);

    void delete(Long menuId);
}
