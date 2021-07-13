package com.zhy.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SysMenuSaveRequest {
    private Long parentId;

    @NotBlank
    private String name;

    private String path;

    @NotBlank
    private String perms;

    private String component;

    private Integer type;

    private String icon;

    private Integer orderNum;
}
