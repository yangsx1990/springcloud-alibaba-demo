package com.provider.config;

import com.lmax.disruptor.EventFactory;
import com.provider.model.MessageEvent;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
public class MessageEventFactory<T> implements EventFactory<MessageEvent<T>> {

    public MessageEventFactory() {
    }

    public MessageEvent<T> newInstance() {
        return new MessageEvent();
    }


}
