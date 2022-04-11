package com.provider.config;

import com.lmax.disruptor.EventHandler;
import com.provider.model.MessageEvent;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-04-06
 */
public class CleaningEventHandler<T> implements EventHandler<MessageEvent<T>> {
    @Override
    public void onEvent(MessageEvent<T> event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("enter CleaningEventHandler……");
        event.clear();
    }
}
