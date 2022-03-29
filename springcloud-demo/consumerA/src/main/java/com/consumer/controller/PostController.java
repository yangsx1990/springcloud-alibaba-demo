package com.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.consumer.feign.MyFeign;
import com.consumer.model.Result;
import com.consumer.model.UserA;
import org.springframework.context.annotation.Bean;
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
public class PostController {

    @Resource
    MyFeign myFeign;

    @GetMapping("/hystrix")
    @SentinelResource(value = "hystrix", blockHandler = "handlerFallback")
    public Result getBySingParam(String param) {
        return myFeign.test(new UserA(1, param), "aaa");
    }

    public Result handlerFallback(String param,BlockException  e) {
        return new Result(999,"统一处理的异常返回内容",e.getMessage());
    }


    @GetMapping("/demo")
    @SentinelResource(value = "demo", fallback = "testFlow")
    public Result test() {
        return myFeign.test(new UserA(1, "demo"), "aaa");
    }

    public Result testFlow(BlockException e){
        String msg="";
        if(e instanceof FlowException){
            msg="接口限流了。。。";
        }else if(e instanceof DegradeException){
            msg="接口降级了。。。";
        }else if(e instanceof ParamFlowException){
            msg="热点参数限流了。。。";
        }else if(e instanceof SystemBlockException){
            msg="触发系统保护。。。";
        }else if(e instanceof AuthorityException){
            msg="授权规则验证失败。。。";
        }else{
            msg="其他规则异常。。。";
        }
        return new Result(999,msg,null);

    }

    }
