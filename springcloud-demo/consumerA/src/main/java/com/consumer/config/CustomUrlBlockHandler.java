package com.consumer.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.consumer.model.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-28
 */
//@Component
@Slf4j
public class CustomUrlBlockHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {
        log.info("[CustomUrlBlockHandler] {}",e.getRule());

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
        new ObjectMapper().writeValue(httpServletResponse.getWriter(),new Result(999,msg,null));
    }
}
