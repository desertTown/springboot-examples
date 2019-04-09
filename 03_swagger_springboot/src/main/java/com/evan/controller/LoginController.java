package com.evan.controller;

import com.evan.VO.ResultVO;
import com.evan.bean.LoginBean;
import com.evan.pojo.User;
import com.evan.service.LoginService;
import com.evan.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "后台管理", tags = "Administrator - 后台主入口")
@RequestMapping("/user")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    @ApiOperation(value = "用户登录——可用", httpMethod = "POST", response = ResultVO.class)
    public ResultVO login(@RequestBody LoginBean loginBean) throws Exception {
        User user = new User();
        BeanUtils.copyProperties(loginBean, user);
        boolean result = loginService.login(user);
        if (result) {
            return ResultVOUtil.success("login success");
        } else {
            return ResultVOUtil.fail("login fail");
        }
    }

}
