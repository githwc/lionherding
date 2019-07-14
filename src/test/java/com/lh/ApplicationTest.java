package com.lh;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.sql.ConnectionEvent;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Map;

/**
 * DESC：测试用例
 *  @RunWith(SpringRunner.class)：一个运行器
 *  @SpringBootTest：指明这是一个测试用例
 *  @WebAppConfiguration：
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class ApplicationTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Test
    public void whenQuerySuccess()throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/user")
                .param("id","2323233223")
                .param("name","贝尔")
                .param("password","232332")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3));
    }


    @Before
    public void before(){
        System.out.println("当前类======ApplicationTest.before()开始了");
    }

    @After
    public void after(){
        System.out.println("当前类======ApplicationTest.after()结束了");
    }



    //测试druid监控
    @Resource
    private DataSource dataSource;

    @Test
    public void testConnection() throws Exception {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(dataSource);
        connection.close();
    }


}
