package com.vinnet.dao;

import com.vinnet.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {
    List<Order> findByUserId(Integer userId);
}
