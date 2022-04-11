package com.provider.config;

import java.time.LocalDateTime;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
public class Consumer extends DisruptorConsumer<String>{
    private String name;

    public Consumer(String name) {
        this.name = name;
    }

    public void consume(String data) {
        System.out.println(Thread.currentThread().getName()+":"+this.name + "：拿到队列中的数据：" + data);
        //等待1秒钟
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
