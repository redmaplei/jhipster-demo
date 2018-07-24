package com.mycompany.myapp.service;

import com.mycompany.myapp.Demo3App;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


/**
 * Create by wys on 2018/7/23
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3App.class)
public class BicycleAdminLogin_serviceTest {

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders
            .webAppContextSetup(this.wac)
            .build();
    }

    @Test
    public void BicycleAdminLoginloginTest() throws Exception{
        this.mockMvc.perform(post("/api/bicycle-admin-login")
                                .param("username","a")
                                .param("password","a")
        ).andExpect(status().isOk())
            .andDo(print())
            .andExpect(model().attribute("username","a"))
            .andExpect(model().attribute("password","a"))

            .andExpect(view().name("result"));
    }

    @Autowired
    BicycleAdminService bicycleAdminService;

    @Test
    public void RLogin() {
        System.out.println(bicycleAdminService.login("",""));
    }

}
