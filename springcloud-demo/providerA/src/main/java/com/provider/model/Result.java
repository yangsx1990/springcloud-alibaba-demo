package com.provider.model;

import lombok.Data;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-22
 */
@Data
public class Result {
    private int code;

    private String message;

    private Object object;

    public Result(){

    }
    public Result(int code,String message,Object object){
        this.code=code;
        this.message=message;
        this.object=object;
    }
}
