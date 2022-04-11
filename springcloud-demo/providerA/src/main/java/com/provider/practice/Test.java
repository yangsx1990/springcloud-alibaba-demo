package com.provider.practice;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import lombok.Data;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-04-06
 */
public class Test {

    public static void main(String[] args) {
        //1、创建一个disruptor（ringbuffer）
        Disruptor<ObjectEvent<String>> disruptor=new Disruptor<ObjectEvent<String>>(
                new MyEventFactory<ObjectEvent<String>>(),4, Executors.defaultThreadFactory()
                , ProducerType.SINGLE,new BlockingWaitStrategy()
        );
        //2、指定消费者
        disruptor.handleEventsWithWorkerPool(new MyConsumer2(),new MyConsumer3());

        //3、启动
        disruptor.start();
        RingBuffer<ObjectEvent<String>> ringBuffer = disruptor.getRingBuffer();
        AtomicInteger atomicInteger=new AtomicInteger();

        //4、生产者
        for (int i = 0; i <10 ; i++) {
            ringBuffer.publishEvent((e,s)->{
                int temp = atomicInteger.incrementAndGet();
                e.setObj(temp+"");
                System.out.println("producer----- message:"+temp);
//                System.out.println("ringbuffer:"+ringBuffer.toString());

            });
        }
        //关闭
        System.out.println("start to shutdown,disruptor:"+disruptor);
        disruptor.shutdown();


    }

    @Data
    static class ObjectEvent<T>{
        private T obj;

    }

     static class MyEventFactory<T> implements EventFactory<ObjectEvent<String>>{

        @Override
        public ObjectEvent<String> newInstance() {
            return new ObjectEvent<>();
        }
    }

    //消费者
    static class MyConsumer implements EventHandler{

        @Override
        public void onEvent(Object event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println("enter consumer:"+event);
            Thread.sleep(100);
        }
    }

    static class MyConsumer1 implements EventHandler{

        @Override
        public void onEvent(Object event, long sequence, boolean endOfBatch) throws Exception {
            System.out.println("enter consumer1:"+event);
            Thread.sleep(100);
        }
    }

    static class MyConsumer2 implements WorkHandler{

        @Override
        public void onEvent(Object event) throws Exception {
            System.out.println("enter consumer2:"+event);
            Thread.sleep(10000);
        }
    }

    static class MyConsumer3 implements WorkHandler{

        @Override
        public void onEvent(Object event) throws Exception {
            System.out.println("enter consumer3:"+event);
            Thread.sleep(10000);
        }
    }
}
