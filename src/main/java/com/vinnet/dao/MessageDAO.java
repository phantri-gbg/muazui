package com.vinnet.dao;

import com.vinnet.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageDAO extends JpaRepository<Message, Integer> {
    List<Message> findBySenderIdOrReceiverId(Integer senderId, Integer receiverId);
}
