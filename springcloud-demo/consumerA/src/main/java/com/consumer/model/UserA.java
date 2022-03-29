package com.consumer.model;

import lombok.Data;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-18
 */
@Data
public class UserA {
    private Integer id;

    private String name;

    public UserA(){}
    public UserA(Integer id,String name){
        this.id=id;
        this.name=name;
    }
    @Override
    public String toString() {
        return "UserA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
