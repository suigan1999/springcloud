package com.bianyiit.controller;

import com.bianyiit.cilent.AccountClient;
import com.bianyiit.domian.Account;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
//@DefaultProperties(defaultFallback = "fallBack") // 指定一个类的全局熔断方法
public class AccountController {
   /* @Autowired
    RestTemplate restTemplate;*/
    @Autowired
    AccountClient accountClient;
    @Autowired
    DiscoveryClient discoveryClient;//服务消费方在eureka注册中心拉取的服务列表都封装到DiscoveryClient里面

    @RequestMapping("/account/{id}")
    // @HystrixCommand(fallbackMethod = "fallBack")//局部熔断
    //@HystrixCommand
    public Account findAccountByid(@PathVariable("id") Integer id){
        //return restTemplate.getForObject("http://localhost:8082/"+id,Account.class);
        // 根据服务名称，获取服务实例。有可能是集群，所以是service实例集合
      /*  List<ServiceInstance> instances = discoveryClient.getInstances("server-provider");
        // 因为只有一个Service-provider。所以获取第一个实例
        ServiceInstance instance = instances.get(0);
        String url="http://"+instance.getHost()+":"+instance.getPort()+"/"+id;
        System.out.println(url);*/
        //开启负载均衡之后不再手动获取ip和端口，而是直接通过服务名称调用：

      /*  String url="http://server-provider/"+id;
        return restTemplate.getForObject(url,Account.class);*/
        return accountClient.getAccountById(id);
    }

    /**
     *开启熔断服务降级步骤
     * 1.引入Hystrix的依赖
     * 2.在消费方启动类添加@EnableCircuitBreaker注解
     * 3.在controller里面编写服务降级的方法
     *     编写局部降级方法
     *        1.要求方法的返回值和参数必须与被降级的方法保持一致
     *        2.在被降级的方法上使用 @HystrixCommand(fallbackMethod = "fallBack")
     *     编写全局服务降级方法
     *        1.只需要方法返回值和降级的方法一致即可
     *        2.在消费类上面使用
     *        3.在被降级的方法上使用 @HystrixCommand注解
     *
     */
    //定义降级方法
    public Account fallBack(){

        Account account = new Account();
        account.setId(404);
        return account;
    }
}
