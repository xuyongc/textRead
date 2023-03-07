package com.xu.textread;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @Author xyc
 */
@SpringBootApplication
@MapperScan("com.xu.textread.mapper")
public class TextReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(TextReadApplication.class, args);
    }

}
