package com.wbst.Timer;

import com.wbst.domain.Corn;
import com.wbst.dto.WarningDataDto;
import com.wbst.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.util.Date;
//@Configuration      //1.主要用于标记配置类，兼备Component的效果。
//@EnableScheduling
public class AutomaticBackupTimer implements SchedulingConfigurer {

    @Autowired
    private SystemService systemService;
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //执行将异常数据添加到异常表中
        //添加任务内容
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//              System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime());
                systemService.systembackups("周期内自动备份");
            }
        };

        //设置执行周期
        Trigger trigger = new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                return null;
            }
        };
        scheduledTaskRegistrar.addTriggerTask(runnable,triggerContext ->{
            //2.1 从数据库获取执行周期
            String cron = systemService.findAutomaticModel();

            //2.3 返回执行周期(Date)
            return new CronTrigger(cron).nextExecutionTime(triggerContext);
        });
    }
}
