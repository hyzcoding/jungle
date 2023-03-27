package com.hyzcoding.jungle.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 * @date 2019/3/11
 * @since 1.0.0
 */
@SpringBootApplication(scanBasePackages = {"com.hyzcoding.jungle"})
//@EnableConfigServer
//@RefreshScope
@EnableZuulProxy
@EnableEurekaClient
@EnableSwagger2
@EnableFeignClients(basePackages =  {"com.hyzcoding.jungle"})
//@EnableRedisHttpSession
public class ZuulApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }
}
