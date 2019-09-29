package com.lh.modules.TestTemp;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-23
 * @Version: 1.0.0
 */
// @Configuration
public class QuartzConfiguration {

    // // 使用jobDetail包装job
    // @Bean
    // public JobDetail myJobDetail() {
    //     return JobBuilder.newJob(MyJob.class).withIdentity("myJob").storeDurably().build();
    // }
    //
    // // 把jobDetail注册到trigger上去
    // @Bean
    // public Trigger myJobTrigger() {
    //     SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
    //             .withIntervalInSeconds(15).repeatForever();
    //
    //     return TriggerBuilder.newTrigger()
    //             .forJob(myJobDetail())
    //             .withIdentity("myJobTrigger")
    //             .withSchedule(scheduleBuilder)
    //             .build();
    // }
    //
    // // 使用jobDetail包装job
    // @Bean
    // public JobDetail myCronJobDetail() {
    //     return JobBuilder.newJob(MyCronJob.class).withIdentity("myCronJob").storeDurably().build();
    // }
    //
    // // 把jobDetail注册到Cron表达式的trigger上去
    // @Bean
    // public Trigger CronJobTrigger() {
    //     CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
    //
    //     return TriggerBuilder.newTrigger()
    //             .forJob(myCronJobDetail())
    //             .withIdentity("myCronJobTrigger")
    //             .withSchedule(cronScheduleBuilder)
    //             .build();
    // }
}
