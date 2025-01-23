package com.hyzcoding.jungle.zipkin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import zipkin2.server.internal.EnableZipkinServer;

/**
 * 〈一句话功能简述〉<br>
 * 〈 〉
 *
 * @author hyz
 *  2019/4/3
 * @since 1.0.0
 */

@EnableDiscoveryClient
@EnableZipkinServer
@SpringBootApplication
public class ZipkinServiceApplication {
    private static Logger logger = LoggerFactory.getLogger(ZipkinServiceApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ZipkinServiceApplication.class, args);
    }
}