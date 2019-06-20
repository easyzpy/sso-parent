package com.zhangpingyang.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "view")
public class ViewController {
    private static final String DEFAULT_URL = "http://www.codeshop.com:9010/view/index";
    /**
     * 跳转登录页面
     * @return
     */
    @GetMapping("/login")
    public String toLogin(@RequestParam(required = false, defaultValue = DEFAULT_URL)
                                      String target, HttpSession session) {
        // TODO:
        session.setAttribute("target", target);
        return "login";
    }

}
