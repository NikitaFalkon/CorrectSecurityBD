package com;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginTest
{
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void test() throws Exception
    {
         this.mockMvc.perform(get("/"))
                 .andDo(print())
                 .andExpect(status().isOk())
                 .andExpect(content().string(containsString("Hello")));
    }
    @Test
    public void menu() throws Exception
    {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost:8080/login"));
    }
    @Test
    public void correctLoginTest() throws Exception
    {
        this.mockMvc.perform(formLogin().user("u").password("p"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
    @Test
    public void badCreditials() throws Exception
    {
        this.mockMvc.perform(post("/login").param("notuser","notpassword"))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
