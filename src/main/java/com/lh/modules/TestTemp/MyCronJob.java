package com.lh.modules.TestTemp;

import java.util.Date;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-22
 * @Version: 1.0.0
 */

// @Configuration
// @EnableScheduling
public class MyCronJob  {

    // @Scheduled(cron = "0/1 * * * * ?")
    public void run(){
        System.out.println("当前类======MyCronJob.run()"+ new Date());
    }
    // @Override
    // protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
    //     System.out.println("任务执行了" + new Date());
    //     // indexController.testMail();
    // }
}
