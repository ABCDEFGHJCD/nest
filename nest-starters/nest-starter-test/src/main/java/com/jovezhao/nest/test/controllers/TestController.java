package com.jovezhao.nest.test.controllers;

import com.jovezhao.nest.dubbo.DubboResource;
import com.jovezhao.nest.test.api.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaofujun on 2017/6/29.
 */
@RestController
public class TestController {
    @GetMapping("/hello")
    public String hello() {
        String ffff = userService.changeName("ffff");
        return "sss"+ffff;
    }

    @DubboResource
    UserService userService;
}
