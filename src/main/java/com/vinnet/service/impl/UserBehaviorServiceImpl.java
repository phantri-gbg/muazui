package com.vinnet.service.impl;

import com.vinnet.dao.UserBehaviorDAO;
import com.vinnet.model.UserBehavior;
import com.vinnet.service.interfaces.UserBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBehaviorServiceImpl implements UserBehaviorService {
    @Autowired
    private UserBehaviorDAO userBehaviorDAO;

    @Override
    public List<UserBehavior> findByUserId(Integer userId) {
        return userBehaviorDAO.findByUserId(userId);
    }

    @Override
    public UserBehavior save(UserBehavior behavior) {
        return userBehaviorDAO.save(behavior);
    }

    @Override
    public List<UserBehavior> findAll() {
        return userBehaviorDAO.findAll();
    }
}
