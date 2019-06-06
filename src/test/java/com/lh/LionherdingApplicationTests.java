package com.lh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.sql.ConnectionEvent;
import javax.sql.DataSource;
import java.sql.Connection;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LionherdingApplicationTests {

	@Test
	public void contextLoads() {

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
