package com.zhy.controller;

import com.zhy.model.dto.ApiResponse;
import com.zhy.model.dto.CaptchaGenerateResponse;
import com.zhy.model.dto.CaptchaVerifyRequest;
import com.zhy.service.CaptchaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *  * @author zhy
 */
@Api(tags = "验证码管理")
@RestController
@RequestMapping("/api/captcha")
public class CaptchaController {
    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/generate")
    public ApiResponse<CaptchaGenerateResponse> generate() {
        return ApiResponse.ok("验证码获取成功",
                captchaService.generate()
        );
    }

    @PostMapping("/verify")
    public ApiResponse<Object> verify(@Validated @RequestBody CaptchaVerifyRequest verifyRequest) {
        captchaService.verify(verifyRequest);
        return ApiResponse.ok("验证成功");
    }
}
