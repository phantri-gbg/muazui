package com.vinnet.service.interfaces;

import com.vinnet.model.UserBehavior;

import java.util.List;

public interface UserBehaviorService {
    List<UserBehavior> findByUserId(Integer userId);
    UserBehavior save(UserBehavior behavior);
    List<UserBehavior> findAll();
}