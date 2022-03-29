package com.consumer.controller;

//import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.consumer.feign.MyFeign;
import com.consumer.model.Result;
import com.consumer.model.UserA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-23
 */
@RestController
@RequestMapping("/consumer")
public class GetController {

//    @Resource
    MyFeign myFeign;

    @GetMapping("/get/singleParam")
    public Result getBySingParam(HttpServletRequest request, @RequestParam("param") String param) {
        System.out.println("从request中获取：["+request.getContentType()+"] token:"+request.getHeader("token"));
        return myFeign.test(new UserA(1,param),"s");
    }

    @GetMapping("/get/sentinel")
    //@SentinelResource(value = "/consumer/get/sentinel",fallback = "handlerFallback",blockHandler = "handlerFallback")
    public Result getSentinel(HttpServletRequest request, @RequestParam("param") String param) {
        System.out.println("从request中获取：["+request.getContentType()+"] token:"+request.getHeader("token"));
        return myFeign.test(new UserA(1,param),"s");
    }

    public String handlerFallback(@RequestParam("param") String param, Throwable e) {
        return "统一处理的异常返回内容：param-"+param;
    }
}
