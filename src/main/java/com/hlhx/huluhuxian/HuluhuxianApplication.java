package com.hlhx.huluhuxian;

import com.hlhx.huluhuxian.DataBaseConfig.DynamicDataSourceRegister;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Import({DynamicDataSourceRegister.class}) // 注册动态多数据源
@EnableScheduling // 启动类启用定时
@MapperScan(basePackages = "com.hlhx.huluhuxian.mapper")
public class HuluhuxianApplication {

    public static void main(String[] args) {
        SpringApplication.run(HuluhuxianApplication.class, args);
    }

}
