package com.dx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: test
 * @description:
 * @author: dx
 * @create: 2023/7/31 20:15
 */
@SpringBootApplication
@MapperScan("com.dx.Mapper")
public class XxlJobStudy {
    public static void main(String[] args) {
        SpringApplication.run(XxlJobStudy.class, args);
    }
}
