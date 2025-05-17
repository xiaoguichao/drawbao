package com.windpicker.drawbao.controller;

import com.windpicker.drawbao.config.ApiResponse;
import com.windpicker.drawbao.model.UserAccount;
import com.windpicker.drawbao.service.UserAccountService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserAccountController {

    @Autowired
    private UserAccountService userAccountService;

    @PostMapping("/login")
    public ApiResponse<Map<String, Object>> login(@RequestBody LoginRequest request) {
        try {
            UserAccount user = userAccountService.login(request.getCode(), request.getOpenid());

            Map<String, Object> result = new HashMap<>();
            result.put("openid", user.getWxid());
            result.put("chances", user.getChances());
            result.put("rewards", user.getRewards());

            return ApiResponse.ok(result);
        } catch (Exception e) {
            return ApiResponse.error("登录失败: " + e.getMessage());
        }
    }

    @Data
    public static class LoginRequest {
        private String code;
        private String openid;
    }
}

