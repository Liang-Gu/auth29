package com.wbst;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@EnableJms//启动消息队列
@SpringBootApplication
@ImportResource("classpath:spring-dubbo.xml")
@MapperScan("com.wbst.mapper")
@EnableScheduling//开启定时任务开关
public class EventServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EventServiceApplication.class,args);
        System.out.println("事件服务端启动成功！！！！！！！！！！！！！！");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
