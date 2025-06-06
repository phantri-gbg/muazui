package com.vinnet.service.impl;

import com.vinnet.dao.MessageDAO;
import com.vinnet.model.Message;
import com.vinnet.service.interfaces.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDAO messageDAO;

    @Override
    public List<Message> findByUserId(Integer userId) {
        return messageDAO.findBySenderIdOrReceiverId(userId, userId);
    }

    @Override
    public Message save(Message message) {
        return messageDAO.save(message);
    }
}