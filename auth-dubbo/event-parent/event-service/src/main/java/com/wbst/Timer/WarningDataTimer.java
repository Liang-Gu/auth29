package com.wbst.Timer;

import com.wbst.domain.Corn;
import com.wbst.domain.Person;
import com.wbst.domain.WarningData;
import com.wbst.dto.WarningDataDto;
import com.wbst.mapper.CornMapper;
import com.wbst.service.PersonService;
import com.wbst.service.WarningDataService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/**
 * 动态实现定时任务
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class WarningDataTimer implements SchedulingConfigurer {

    @Resource
    private CornMapper cornMapper;

    @Reference
    private PersonService personService;

    @Reference
    private WarningDataService warningDataService;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {


        //执行将异常数据添加到异常表中
        //添加任务内容
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
//                System.out.println("执行动态定时任务: " + LocalDateTime.now().toLocalTime());
                Corn corn = cornMapper.findCorn();
                //获取自定义的间隔时长
                Integer abnormalDuration = corn.getAbnormalDuration();
                //获取周期内符合条件的数据
                List<WarningDataDto> personList=personService.findWarningData(new Date(),abnormalDuration);
                //保存异常数据到异常表中
                warningDataService.save(personList);
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
            Corn cron = cornMapper.findCorn();
            //2.2 合法性校验.

            //2.3 返回执行周期(Date)
            return new CronTrigger(cron.getCornString()).nextExecutionTime(triggerContext);
        });

    }
}
