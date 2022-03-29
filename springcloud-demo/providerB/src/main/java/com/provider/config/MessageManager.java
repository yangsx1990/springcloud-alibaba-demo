package com.provider.config;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.provider.model.MessageVO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
@Configuration
public class MessageManager {


    @Bean("MessageVO")
    public RingBuffer<MessageVO> MessageVORingBuffer(){
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        MessageFactory factory=new MessageFactory();

        int bufferSize=1024*256;
        Disruptor<MessageVO> disruptor=new Disruptor<MessageVO>(factory,bufferSize,executorService,
                ProducerType.SINGLE,new BlockingWaitStrategy());

        disruptor.handleEventsWith(new MessageEventHandler());

        disruptor.start();

        return disruptor.getRingBuffer();
    }
}
