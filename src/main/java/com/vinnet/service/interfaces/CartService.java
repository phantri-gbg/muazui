package com.vinnet.service.interfaces;

import com.vinnet.model.Cart;

import java.util.List;

public interface CartService {
    List<Cart> findByUserId(Integer userId);
    void addToCart(Integer userId, Integer productId, Integer quantity);
    void updateQuantity(Integer cartId, Integer quantity);
    void removeFromCart(Integer cartId);
}
