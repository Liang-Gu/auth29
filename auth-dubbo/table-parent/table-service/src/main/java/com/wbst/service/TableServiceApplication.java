package com.wbst.service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

import java.io.IOException;

@SpringBootApplication
@ImportResource("classpath:spring-dubbo.xml")
@MapperScan("com.wbst.mapper")
public class TableServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TableServiceApplication.class,args);
        System.out.println("态势分析服务端启动成功！！！！！！！！！！！！！！");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
