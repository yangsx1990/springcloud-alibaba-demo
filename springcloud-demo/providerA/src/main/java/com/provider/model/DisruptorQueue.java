package com.provider.model;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.time.LocalDateTime;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
public class DisruptorQueue <T> {
    private final RingBuffer<MessageEvent<T>> ringBuffer;

    public DisruptorQueue(Disruptor<MessageEvent<T>> disruptor) {
        this.ringBuffer = disruptor.getRingBuffer();
        disruptor.start();
    }

    public void add(T t) {

        if (t == null) {
            return;
        }
        long sequence = this.ringBuffer.next();
        try {
            MessageEvent<T> event = this.ringBuffer.get(sequence);
            System.out.println("next："+sequence+" 原data:"+event+" 现data："+t);
            event.setObj(t);
        } finally {
            this.ringBuffer.publish(sequence);
        }

    }

    public long size(){
        return this.ringBuffer.getBufferSize();
    }
}
