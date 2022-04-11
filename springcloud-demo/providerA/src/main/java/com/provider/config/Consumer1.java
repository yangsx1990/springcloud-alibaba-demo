package com.provider.config;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-04-01
 */
public class Consumer1 implements EventHandler {

    @Override
    public void onEvent(Object event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println(Thread.currentThread().getName()+ "：Consumer1 拿到队列中的数据：" + event);

        //等待1秒钟
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
