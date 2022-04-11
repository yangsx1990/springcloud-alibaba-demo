package com.consumer.feign.v1;

import com.consumer.model.Result;
import com.consumer.model.UserA;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-23
 */
@FeignClient(value = "provider",fallback = MyFeignFallback.class)
public interface MyFeign {

    @GetMapping("/provider/get/singleParam")
    Result test(@SpringQueryMap UserA param, @RequestHeader("token") String token);

}
