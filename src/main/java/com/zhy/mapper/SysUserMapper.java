package com.zhy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhy.model.entity.SysPerm;
import com.zhy.model.entity.SysRole;
import com.zhy.model.entity.SysUser;

import java.util.List;

/**
 * @author zhy
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysRole> selectRoleById(Long id);

    IPage<SysRole> selectRoleById(IPage page, Long id);

    List<SysPerm> selectMenuById(Long id);

    IPage<SysPerm> selectMenuById(IPage page, Long id);

}
