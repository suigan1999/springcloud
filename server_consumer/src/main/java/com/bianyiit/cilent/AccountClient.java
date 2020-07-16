package com.bianyiit.cilent;

import com.bianyiit.domian.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "server-provider",fallback = AccountClientFallback.class)        //标注这是一个feign接口
public interface AccountClient {

    @GetMapping("{id}")
    public Account getAccountById(@PathVariable("id") Integer id);
    /**
     * 使用fegin步骤
     * 1.引入Fegin依赖
     * 2.在启动类上开启对Fegin客户端的支持@EnableFeignClients
     * 3.自定义Fegin客户端
     *    创建一个接口在接口上面添加@FeignClient注解
     *    将server提供方的方法复制过来（不需要方法体）
     * 4.修改消费方 调用方式
     *   将之前的远程调用服务的RestTemplate删掉
     *   注入Fegin客户端
     */
}
