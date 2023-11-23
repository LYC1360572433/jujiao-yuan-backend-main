package com.lyc.jujiao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 *
 */
@SpringBootApplication
@EnableScheduling
@MapperScan("com.lyc.jujiao.mapper")
public class JujiaoBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(JujiaoBackendApplication.class, args);
    }
}
