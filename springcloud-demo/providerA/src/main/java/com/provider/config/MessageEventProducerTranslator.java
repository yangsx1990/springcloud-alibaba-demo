package com.provider.config;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;
import com.provider.model.MessageEvent;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-04-06
 */
public class MessageEventProducerTranslator {

    private final RingBuffer<MessageEvent> ringBuffer;

    public MessageEventProducerTranslator(RingBuffer<MessageEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<MessageEvent,String> translator=new EventTranslatorOneArg<MessageEvent, String>() {
        @Override
        public void translateTo(MessageEvent event, long sequence, String arg0) {
            event.setObj(arg0);
        }
    };

    public void onData(String content){
        ringBuffer.publishEvent(translator,content);
    }
}
