package com.vinnet.dao;

import com.vinnet.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartDAO extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserId(Integer userId);
    Cart findByUserIdAndProductId(Integer userId, Integer productId);
}
