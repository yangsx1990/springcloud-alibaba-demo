package com.provider.controller;

import com.provider.model.Result;
import com.provider.model.UserA;
import com.provider.service.MessageService;
import org.apache.logging.log4j.message.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-23
 */
@RestController
public class MyController {

    @GetMapping("/provider/get/singleParam")
    public Result getBySingParam(HttpServletRequest request, UserA param, @RequestHeader("token")String token) {
        System.out.println("从request中获取：["+request.getContentType()+"] token:"+request.getHeader("token"));
        return new Result(0,"success","providerB:"+param);
    }

    @Resource
    MessageService messageService;

    @GetMapping("/message/put")
    public Result put(String message){
        messageService.put(message);
        return new Result();
    }

    @GetMapping("/message/get")
    public Result put(long index){
        return new Result(0,"success",messageService.get(index));
    }
}
