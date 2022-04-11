package com.provider.model;

import lombok.Data;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
@Data
public class MessageEvent <T>{
    private T obj;
    public MessageEvent() {}
    public MessageEvent(T obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return "MessageEvent{" +
                "obj=" + obj +
                '}';
    }

    public void clear(){
        System.out.println("start clean");
        obj=null;
    }
}
