package com.provider;

import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.provider.config.*;
import com.provider.model.DisruptorQueue;
import com.provider.model.MessageEvent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        // 创建一个消费者
        Consumer myConsumer = new Consumer("---->消费者1");

        // 创建一个Disruptor队列操作类对象（RingBuffer大小为4，false表示只有一个生产者）
//        DisruptorQueue disruptorQueue = DisruptorQueueFactory.getWorkPoolQueue(4,
//                false, myConsumer);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        Disruptor<MessageEvent> _disruptor = new Disruptor(new MessageEventFactory(),
                4, threadFactory,
                 ProducerType.SINGLE,
                new SleepingWaitStrategy());
        _disruptor.handleEventsWith(new Consumer1(),new Consumer2());//.then(new CleaningEventHandler());
        DisruptorQueue disruptorQueue=new DisruptorQueue(_disruptor);

        // 创建一个生产者，开始模拟生产数据
        //Producer myProducerThread = new Producer("生产者1", disruptorQueue);
        ProducerTranslator myProducerThread = new ProducerTranslator("生产者 t1", new MessageEventProducerTranslator(_disruptor.getRingBuffer()));
        Thread t1 = new Thread(myProducerThread);
        t1.start();

        // 执行3s后，生产者不再生产
        Thread.sleep(3 * 1000);
        myProducerThread.stopThread();

        _disruptor.shutdown();
        Thread.sleep(100000);
    }
}
