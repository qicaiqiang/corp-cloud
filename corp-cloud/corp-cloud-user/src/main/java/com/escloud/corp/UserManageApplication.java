package com.escloud.corp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description
 * @Author qicaizhao
 * @Date {date}
 */
@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"com.escloud.corp"})
@EnableSwagger2
@EnableFeignClients
public class UserManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManageApplication.class, args);
    }
}
