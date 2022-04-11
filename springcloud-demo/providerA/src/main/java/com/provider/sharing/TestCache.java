package com.provider.sharing;

import lombok.Data;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-04-01
 */
public class TestCache {
    public static void main(String[] args) {
        User user=new User();

        long start=System.currentTimeMillis();
        Thread t1=new Thread(()->{
            for (int i = 0; i <100000000 ; i++) {
                user.x++;
            }
        });

        Thread t2=new Thread(()->{
            for (int i = 0; i <100000000 ; i++) {
                user.y++;
            }
        });

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(user+"，耗时："+(System.currentTimeMillis()-start));

    }


}
