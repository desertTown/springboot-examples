package com.evan.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class LoginController {

    @GetMapping("index")
    public String login() {
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password
                        ) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return "login";
        } else {
            String token = "asdasfas";   // token should get from authentication server
//            return "forward:/secured/welcome";
            return "redirect:/secured/welcome?token="+token+"&username="+username;
        }
    }
}
