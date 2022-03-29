package com.provider.config;

import com.lmax.disruptor.EventFactory;
import com.provider.model.MessageVO;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
public class MessageFactory implements EventFactory<MessageVO> {
    @Override
    public MessageVO newInstance() {
        return new MessageVO();
    }
}
