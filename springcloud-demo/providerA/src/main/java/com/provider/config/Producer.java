package com.provider.config;

import com.provider.model.DisruptorQueue;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
public class Producer implements Runnable{
    private final String name;
    private final DisruptorQueue disruptorQueue;
    private volatile boolean flag = true;
    private static final AtomicInteger count = new AtomicInteger();

    public Producer(String name, DisruptorQueue disruptorQueue) {
        this.name = name;
        this.disruptorQueue = disruptorQueue;
    }

    @Override
    public void run() {
        try {
            System.out.println( this.name + "：线程启动。");
            while (flag) {
                String data = count.incrementAndGet()+"";
                // 将数据存入队列中
                disruptorQueue.add(data);
                System.out.println( this.name + "：存入" + data + "到队列中。");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println( this.name + "：退出线程。");
        }
    }

    private LocalDateTime now(){
        return LocalDateTime.now();
    }

    public void stopThread() {
        this.flag = false;
    }
}
