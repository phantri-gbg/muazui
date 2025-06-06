package com.vinnet.service.interfaces;

import com.vinnet.model.Notification;

import java.util.List;

public interface NotificationService {
    List<Notification> findByUserId(Integer userId);
    Notification save(Notification notification);
    void markAsRead(Integer notificationId);
}