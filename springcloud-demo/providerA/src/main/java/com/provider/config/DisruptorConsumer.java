package com.provider.config;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import com.provider.model.MessageEvent;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
public abstract class DisruptorConsumer <T>  implements EventHandler<MessageEvent<T>>, WorkHandler<MessageEvent<T>> {
    @Override
    public void onEvent(MessageEvent<T> event, long l, boolean b) throws Exception {
        System.out.println("enter workhandler");
       this.consume(event.getObj());
        // this.onEvent(event);
    }

    @Override
    public void onEvent(MessageEvent<T> event) throws Exception {
        this.consume(event.getObj());
    }

    public abstract void consume(T var1);

}
