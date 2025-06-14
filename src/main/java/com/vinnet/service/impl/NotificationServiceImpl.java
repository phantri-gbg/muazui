package com.vinnet.service.impl;

import com.vinnet.dao.NotificationDAO;
import com.vinnet.model.Notification;
import com.vinnet.service.interfaces.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationDAO notificationDAO;

    @Override
    public List<Notification> findByUserId(Integer userId) {
        return notificationDAO.findByUserId(userId);
    }

    @Override
    public Notification save(Notification notification) {
        return notificationDAO.save(notification);
    }

    @Override
    public void markAsRead(Integer notificationId) {
        Notification notification = notificationDAO.findById(notificationId).orElseThrow();
        notification.setIsRead(true);
        notificationDAO.save(notification);
    }
}
