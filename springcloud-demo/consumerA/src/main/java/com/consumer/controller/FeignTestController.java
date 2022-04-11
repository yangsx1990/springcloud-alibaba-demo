package com.consumer.controller;

import com.consumer.feign.v1.MyFeign;
import com.consumer.model.Result;
import com.consumer.model.UserA;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-04-06
 */
@RestController
public class FeignTestController {
    @Resource
    private MyFeign myFeign;

    @GetMapping("feign/test")
    public Result test1(){
        return myFeign.test(new UserA(1,"aaa"),"token");
    }
}
