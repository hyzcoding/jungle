package com.hyzcoding.jungle.article;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * 〈articleServiceApplication启动类〉<br>
 * 〈 〉
 *
 * @author hyz
 * @date 2019/3/12
 * @since 1.0.0
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = {"com.hyzcoding.jungle"})
@MapperScan("com.hyzcoding.jungle.common.dao")
@EnableSwagger2Doc
@EnableElasticsearchRepositories(basePackages = "com.nightriver.jungle.common.repo")
public class ArticleServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ArticleServiceApplication.class, args);
    }
}
