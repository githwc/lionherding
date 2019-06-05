package com.lh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LionherdingApplicationTests {

	@Test
	public void contextLoads() {

	}


    @Resource
    private DataSource dataSource;
    @Test
    public void testConnection() throws Exception {
        System.out.println("===LionHerding===值=" + "23323" + "," + "当前类=LionherdingApplicationTests.testConnection()");
        System.out.println("===LionHerding===值=" + "23323" + "," + "当前类=LionherdingApplicationTests.testConnection()");
        System.out.println(this.dataSource);
    }

}
