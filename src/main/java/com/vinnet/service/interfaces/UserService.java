package com.vinnet.service.interfaces;

import com.vinnet.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    User findById(Integer id);
    void create(User user);
    User update(User user);
    void delete(Integer id);
    Optional<User> findByEmail(String email);

}