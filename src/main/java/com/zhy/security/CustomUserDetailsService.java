package com.zhy.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhy.mapper.SysUserMapper;
import com.zhy.model.entity.SysPerm;
import com.zhy.model.entity.SysRole;
import com.zhy.model.entity.SysUser;
import com.zhy.service.AuthCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhy
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private AuthCacheService cacheService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails cacheUserDetails = cacheService.getUserDetails(username);
        if (cacheUserDetails == null) {
            SysUser user = userMapper.selectOne(new QueryWrapper<SysUser>().eq("username", username));

            if (user == null){
                throw new UsernameNotFoundException("用户不存在");
            }

            CustomUserDetails userDetails = new CustomUserDetails(
                    user.getUsername(),
                    user.getPassword(),
                    user.getStatus(),
                    loadAuthorityByUserId(user.getId())
            );
            cacheService.setUserDetails(userDetails);
            return userDetails;
        }
        return cacheUserDetails;
    }

    public List<GrantedAuthority> loadAuthorityByUserId(Long userId) {
        List<SysRole> roles = userMapper.selectRoleById(userId);
        String roleCodes = roles.stream()
                .map(role -> "ROLE_" + role.getCode())
                .collect(Collectors.joining(",")
                );

        List<SysPerm> menus = userMapper.selectMenuById(userId);
        String menuCodes = menus.stream()
                .map(SysPerm::getPerms)
                .collect(Collectors.joining(",")
                );

        String authorityString = roleCodes.concat(",").concat(menuCodes);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString);
    }
}
