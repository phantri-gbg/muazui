package com.vinnet.service.impl;

import com.vinnet.dao.CartDAO;
import com.vinnet.model.Cart;
import com.vinnet.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDAO cartDAO;

    @Override
    public List<Cart> findByUserId(Integer userId) {
        return cartDAO.findByUserId(userId);
    }

    @Override
    public void addToCart(Integer userId, Integer productId, Integer quantity) {
        Cart existingCart = cartDAO.findByUserIdAndProductId(userId, productId);
        if (existingCart != null) {
            existingCart.setQuantity(existingCart.getQuantity() + quantity);
            cartDAO.save(existingCart);
            return;
        }
        Cart cart = Cart.builder()
                .userId(userId)
                .productId(productId)
                .quantity(quantity)
                .build();
        cartDAO.save(cart);
    }

    @Override
    public void updateQuantity(Integer cartId, Integer quantity) {
        Cart cart = cartDAO.findById(cartId).orElseThrow();
        cart.setQuantity(quantity);
        cartDAO.save(cart);
    }

    @Override
    public void removeFromCart(Integer cartId) {
        cartDAO.deleteById(cartId);
    }
}
