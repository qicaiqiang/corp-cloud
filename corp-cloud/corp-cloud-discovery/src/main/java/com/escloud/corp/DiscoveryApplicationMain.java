package com.escloud.corp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description
 * @Author qicaizhao
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryApplicationMain {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryApplicationMain.class, args);
    }
}
