package com.evan.controller;

import com.evan.bean.LoginBean;
import com.evan.utils.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginControllerTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void should_login_success() throws Exception {
        LoginBean loginBean = new LoginBean();
        loginBean.setName("Evan");
        loginBean.setPassword("123");
        String loginInfo = JsonUtil.toJson(loginBean);
        mockMvc.perform(post("/user/login")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .content(loginInfo))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.data").value("login success"));
    }

}
