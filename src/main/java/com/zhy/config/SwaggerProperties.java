package com.zhy.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhy
 */
@Component
@ConfigurationProperties("app.swagger")
@Data
@EqualsAndHashCode(callSuper = false)
public class SwaggerProperties {
    /**
     * API文档生成基础路径
     */
    private String apiBasePackage;

    private boolean enableSecurity;

    private String title;

    private String description;

    private String version;

    private String contactName;

    private String contactUrl;

    private String contactEmail;
}
