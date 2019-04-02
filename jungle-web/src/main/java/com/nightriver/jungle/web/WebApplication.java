package com.nightriver.jungle.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @create 2019/3/6
 * @since 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.nightriver.jungle"})
@EnableEurekaClient
@EnableFeignClients(basePackages =  {"com.nightriver.jungle"})
@MapperScan("com.nightriver.jungle.common.dao")
@EnableHystrix
public class WebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }
}
