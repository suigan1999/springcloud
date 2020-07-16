package com.bianyiit.cilent;

import com.bianyiit.domian.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountClientFallback implements AccountClient {

    @Override
    public Account getAccountById(Integer id) {
        Account account=new Account();
        account.setId(404);
        return account;
    }
    /*
    * feign服务降级步骤
    * 1.在配置文件中启动熔断
    *    feign.hystrix.enabled: true # 开启Feign的熔断功能
    * 2.定义一个类AccountClientFallback实现AccountClient接口做为fallback的处理类
    * 3.在@FeignClient注解里面添加 fallback
    * @FeignClient(value = "server-provider",fallback = AccountClientFallback.class)
     * */
    /*
    * 开启熔断之后，进行服务降级
    * 只有在提供方出现异常时才会进行降级
    * */
}
