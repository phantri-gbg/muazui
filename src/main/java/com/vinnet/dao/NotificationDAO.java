package com.vinnet.dao;

import com.vinnet.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationDAO extends JpaRepository<Notification, Integer> {
    List<Notification> findByUserId(Integer userId);
}
