package com.zhy.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.stream.Collectors;

/**
 * 解决CustomUserDetails中GrantedAuthority无法被反序列化
 *   1、序列化时：CustomUserDetails -> CustomUserDetailsCacheDto -> redis
 *   2、反序列化时：redis -> CustomUserDetailsCacheDto -> CustomUserDetails
 *
 *   @author zhy
 */
public class UserDetailsCacheDto {
    private String username;

    private String password;

    private int status;

    private String authorities;

    public UserDetailsCacheDto() {
    }

    private UserDetailsCacheDto(String username, String password, int status, String authorities) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.authorities = authorities;
    }

    public static UserDetailsCacheDto create(CustomUserDetails userDetails) {
        return new UserDetailsCacheDto(
                userDetails.getUsername(),
                userDetails.getPassword(),
                userDetails.getStatus(),
                userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","))
        );
    }

    public static CustomUserDetails resume(UserDetailsCacheDto userDetailsCache) {
        return new CustomUserDetails(
                userDetailsCache.getUsername(),
                userDetailsCache.getPassword(),
                userDetailsCache.getStatus(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(userDetailsCache.getAuthorities())
        );
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getStatus() {
        return status;
    }

    public String getAuthorities() {
        return authorities;
    }
}
