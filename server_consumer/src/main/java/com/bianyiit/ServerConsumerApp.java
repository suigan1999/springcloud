package com.bianyiit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/*@SpringBootApplication
@EnableDiscoveryClient //开启Eureka客户端功能
@EnableCircuitBreaker //开启熔断*/
@SpringCloudApplication //相当于@SpringBootApplication @EnableDiscoveryClient @EnableCircuitBreaker三个注解
@EnableFeignClients   //开启Feign客户端
public class ServerConsumerApp {
    public static void main(String[] args) {
        SpringApplication.run(ServerConsumerApp.class,args);
    }

    /**
     * Ribbon默认的负载均衡策略是简单的轮询
     * @return
     */
    /**
     * 删除RestTemplate：feign已经自动集成了Ribbon负载均衡的RestTemplate。
     * 所以，此处不需要再注册RestTemplate。
     */
   /* @Bean
    @LoadBalanced   //开启负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }*/
}
