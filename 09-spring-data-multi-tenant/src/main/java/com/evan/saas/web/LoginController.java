package com.evan.saas.web;


import com.evan.saas.context.TenantContextHolder;
import com.evan.saas.tenant.model.User;
import com.evan.saas.tenant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login.html")
    public String login(){
        return "/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "username") String username, @RequestParam(name = "password")String password, ModelMap model){
        System.out.println("tenant:"+ TenantContextHolder.getTenant());
        User user = userService.findByUsername(username);
        if(user != null){
            if(user.getPassword().equals(password)){
                model.put("user",user);
                return "/index";
            }else{
                return "/login";
            }
        }else{
            return "/login";
        }
    }
}
