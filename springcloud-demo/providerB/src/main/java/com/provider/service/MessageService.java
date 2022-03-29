package com.provider.service;

import com.provider.model.MessageVO;

import java.util.List;

/**
 * @author yangsaixing
 * @version v1.0
 * @description
 * @date 2022-03-29
 */
public interface MessageService {

    void put(String message);

    void putList(List<String> message);
    MessageVO get(long index);
}
