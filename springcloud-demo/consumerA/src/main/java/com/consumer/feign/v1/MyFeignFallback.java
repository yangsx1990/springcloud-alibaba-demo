package com.consumer.feign.v1;

import com.consumer.model.Result;
import com.consumer.model.UserA;
import org.springframework.stereotype.Component;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-24
 */
@Component
public class MyFeignFallback implements MyFeign{
    @Override
    public Result test(UserA param, String token) {
        return new Result(999,"failure",null);
    }
}
