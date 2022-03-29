package com.provider.service.impl;

import com.lmax.disruptor.RingBuffer;
import com.provider.model.MessageVO;
import com.provider.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
@Slf4j
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    RingBuffer<MessageVO> ringBuffer;
    @Override
    public void put(String message) {
        log.info("enter put service");
        long sequence=ringBuffer.next();

        try{
            MessageVO messageVO = ringBuffer.get(sequence);
            messageVO.setMessage(message);
            log.info("put success sequence:{} message:{}",sequence,message);
        }catch (Exception e){
            log.error("put fail");
        }finally {
            ringBuffer.publish(sequence);
        }

    }

    @Override
    public void putList(List<String> message) {
        Iterator<String> var2 = message.iterator();

        while(var2.hasNext()) {
            String t = var2.next();
            if (t != null) {
                this.put(t);
            }
        }
    }

    @Override
    public MessageVO get(long index) {
        return ringBuffer.get(index);
    }
}
