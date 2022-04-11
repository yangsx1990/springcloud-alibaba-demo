package com.provider.config;

import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.provider.model.DisruptorQueue;
import com.provider.model.MessageEvent;

import java.util.concurrent.Executors;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
public class DisruptorQueueFactory {

    public static <T> DisruptorQueue<T> getWorkPoolQueue(int queueSize, boolean isMoreProducer,
                                                         DisruptorConsumer<T>... consumers) {
        Disruptor<MessageEvent<T>> _disruptor = new Disruptor(new MessageEventFactory(),
                queueSize, Executors.defaultThreadFactory(),
                isMoreProducer ? ProducerType.MULTI : ProducerType.SINGLE,
                new SleepingWaitStrategy());
        _disruptor.handleEventsWithWorkerPool(consumers);
        return new DisruptorQueue(_disruptor);
    }

    // 创建"发布订阅模式"的操作队列，即同一事件会被多个消费者并行消费
    public static <T> DisruptorQueue<T> getHandleEventsQueue(int queueSize, boolean isMoreProducer,
                                                             DisruptorConsumer<T>... consumers) {
        Disruptor<MessageEvent<T>> _disruptor = new Disruptor(new MessageEventFactory(),
                queueSize, Executors.defaultThreadFactory(),
                isMoreProducer ? ProducerType.MULTI : ProducerType.SINGLE,
                new SleepingWaitStrategy());
        _disruptor.handleEventsWith(consumers);
        return new DisruptorQueue(_disruptor);
    }
}
