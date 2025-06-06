package com.vinnet.service.interfaces;

import com.vinnet.model.Message;

import java.util.List;

public interface MessageService {
    List<Message> findByUserId(Integer userId);
    Message save(Message message);
}