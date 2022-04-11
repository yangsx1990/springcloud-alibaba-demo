package com.provider.controller;

import com.provider.model.Result;
import com.provider.model.UserA;
import com.provider.sharing.TestCache;
import com.provider.sharing.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
         System.out.println(1/0);
//        try {
//            Thread.sleep(500000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return new Result(0,"success","providerA:"+param);
    }

    @GetMapping("sharing")
    public void test(){
        User user=new User();

//        TestCache.testPointer(user);
    }
}
