package com.mycompany.myapp.web.rest;

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

/**
 * Create by wys on 2018/7/22
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Demo3App.class)
public class RoundServiceTest {

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
    public void RoundServiceCalculatePiPost() throws Exception{
        this.mockMvc.perform(post("/api/calculatePi")
                                .param("radius","10")
                                .param("n","10000")
        ).andExpect(status().isOk());
    }


}
