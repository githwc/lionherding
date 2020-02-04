package com.lh;

import com.lh.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * DESC：测试用例
 *  @RunWith(SpringRunner.class)：一个运行器
 *  @SpringBootTest：指明这是一个测试用例
 *  @WebAppConfiguration：
 */
// @RunWith(SpringRunner.class)
@SpringBootTest
// @WebAppConfiguration
@Slf4j
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
        log.info("当前类======ApplicationTest.before()开始了");
    }

    @After
    public void after(){
        log.info("当前类======ApplicationTest.after()结束了");
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


    @Test
    public void test21() throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setLastLoginTime(LocalDateTime.now());
        if (sysUser.getLastLoginTime().toString().substring(0, 10).equalsIgnoreCase(LocalDateTime.now().toString().substring(0, 10))) {

            log.info("当前类======ApplicationTest.test21()hahahahhahahahhahaha");
        }
        log.info("当前类======ApplicationTest.test21()"+ sysUser.getLastLoginTime().toString().substring(0,10));
    }


    @Test
    public void test222() throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        // // log.info(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+" 00:00:00",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //
        // String dd = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+ " 00:00:00";
        // Date ddd = calculate(formatter.parse(dd),Calendar.DAY_OF_MONTH,1);
        // log.info(formatter.format(ddd));

        String dd = "2019-10-11";
        Date ddd = calculate(formatter.parse(dd),Calendar.DAY_OF_MONTH,1);
        log.info(formatter.format(ddd));
        //
        //
        // String currentDay = formatter.format(new Date());
        // log.info(formatter.format(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())));

        // int day = dayDiff("2019-10-01",currentDay);
        // log.info(day);
        // log.info(currentDay);
        // System.out.println(LocalDateTime.now());
        // String currentStartTime = currentDay + " 00:00:00";
        // String currentEndTime = currentDay + " 23:59:59";
        // System.out.println(currentStartTime);
        // System.out.println(currentEndTime);
    }

    public static Date calculate(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public void localDateTime2Date( LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        System.out.println(date.toString());//Tue Mar 27 14:17:17 CST 2018
    }

    @Test
    public void dayCompareStr(){
        String beginDate = "2020-01-20";
        String endDate = "2020-02-12";

        Period period = Period.between(LocalDate.parse(beginDate), LocalDate.parse(endDate));
        StringBuffer sb = new StringBuffer();
        sb.append(period.getYears()).append(",")
                .append(period.getMonths()).append(",")
                .append(period.getDays());
        System.out.println(sb.toString());

    }

}
