package com.zhangpingyang.login.controller;

import com.zhangpingyang.login.pojo.User;
import com.zhangpingyang.login.utiil.LoginUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "login")
public class LoginController {

//    @Autowired
    private static Set<User> dbUser;
    static {
        dbUser = new HashSet<>();
        dbUser.add(new User(0, "zhangsan", "123456"));
        dbUser.add(new User(1, "lisi", "123456"));
        dbUser.add(new User(2, "wangwu", "123456"));
    }
    @PostMapping("doLogin")
    public String doLogin(User user, HttpSession session, HttpServletRequest request) {
        String target = (String) session.getAttribute("target");
        Stream<User> userStream = dbUser.stream().filter(dbUser -> dbUser.getUsername().equals(user.getUsername())
                && dbUser.getPassword().equals(user.getPassword()));
        Optional<User> first = userStream.findFirst();
        if (first.isPresent()) {
            //保存用户
            String s = UUID.randomUUID().toString();
            LoginUtil.map.put(s, first.get());
        }else{
            //登录失败
            request.setAttribute("msg", "用户名或密码错误");
            return "login";
        }
        //登录成功返回target
        return "redirect:" + target;
    }
}
