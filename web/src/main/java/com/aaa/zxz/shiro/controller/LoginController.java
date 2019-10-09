package com.aaa.zxz.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: 0819shiro
 * @Package: com.aaa.zxz.shiro.controller
 * @ClassName: LoginController
 * @Author: zxz
 * @CreateDate: 2019/8/19 14:14
 * @Version: 1.0
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String Login(){
        return "login";
    }

    @RequestMapping("/")
    public String IndexPage(){
        return "index";
    }
}
