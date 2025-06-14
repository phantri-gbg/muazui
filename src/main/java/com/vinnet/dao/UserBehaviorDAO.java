package com.vinnet.dao;

import com.vinnet.model.UserBehavior;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserBehaviorDAO extends JpaRepository<UserBehavior, Integer> {
    List<UserBehavior> findByUserId(Integer userId);
}
