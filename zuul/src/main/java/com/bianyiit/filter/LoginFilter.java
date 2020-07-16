package com.bianyiit.filter;


import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {
    /**
     * 过滤器类型  pre  前置过滤器
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 过滤器执行顺序  数值越小 优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return 1;
    }
    /**
     * 过滤器是否生效
     *  true 生效   false 不生效
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 编写过滤器的基本逻辑
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //获取Zuul上下文对象
        RequestContext context = RequestContext.getCurrentContext();

        //获取请求对象
        HttpServletRequest request = context.getRequest();
        //设置响应字符集
        context.getResponse().setContentType("text/html;charset=utf-8");
        //获取tom请求头
        String tom = request.getParameter("tom");
        //判断 如果为null过滤qq
        if (StringUtils.isBlank(tom)){
            System.out.println("xxx");
            //过滤该请求，不对其进行路由
            context.setSendZuulResponse(false);
            //设置响应状态码
            context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);

            //设置响应信息
            context.setResponseBody("你缺少一只Tom猫");

        }
        return null;
    }
}
