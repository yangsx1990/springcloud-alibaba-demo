package com.provider.config;

import com.lmax.disruptor.EventHandler;
import com.provider.model.MessageVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
@Slf4j
@Component
public class MessageEventHandler implements EventHandler<MessageVO> {
    @Override
    public void onEvent(MessageVO messageVO, long l, boolean b) throws Exception {
        log.info("enter handler");

    }
}
