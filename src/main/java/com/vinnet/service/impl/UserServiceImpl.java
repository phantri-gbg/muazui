package com.vinnet.service.impl;

import com.vinnet.dao.UserDAO;
import com.vinnet.model.User;
import com.vinnet.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDAO userDAO;

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findById(Integer id) {
        return userDAO.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
    }

    @Override
    public void create(User user) {
        userDAO.save(user);
    }

    @Override
    @Transactional
    public User update(User user) {
        return userDAO.save(user);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        userDAO.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDAO.findByEmail(email);
    }
}