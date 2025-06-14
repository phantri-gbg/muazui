package com.vinnet.service.interfaces;

import com.vinnet.model.Cart;
import com.vinnet.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> findByUserId(Integer userId);
    Order findById(Integer id);
    Order createOrder(Integer userId, List<Cart> cartItems);
    void updateStatus(Integer orderId, String status);
}
