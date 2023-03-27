package com.hyzcoding.jungle.user;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *     用户服务
 * @author         hyz
 * @date     2019/3/9
 * @version        1.0
 **/
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.hyzcoding.jungle"})
@MapperScan("com.hyzcoding.jungle.common.dao")
@EnableSwagger2Doc
@RefreshScope
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
