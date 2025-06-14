package com.vinnet.service.impl;

import com.vinnet.dao.OrderDAO;
import com.vinnet.dao.OrderDetailDAO;
import com.vinnet.dao.ProductDAO;
import com.vinnet.model.Cart;
import com.vinnet.model.Order;
import com.vinnet.model.OrderDetail;
import com.vinnet.model.Product;
import com.vinnet.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderDetailDAO orderDetailDAO;

    @Autowired
    private ProductDAO productDAO;

    @Override
    public List<Order> findByUserId(Integer userId) {
        return orderDAO.findByUserId(userId);
    }

    @Override
    public Order findById(Integer id) {
        return orderDAO.findById(id).orElse(null);
    }

    @Override
    public Order createOrder(Integer userId, List<Cart> cartItems) {
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (Cart cart : cartItems) {
            Product product = productDAO.findById(cart.getProductId()).orElseThrow();
            totalAmount = totalAmount.add(product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
        }

        Order order = Order.builder()
                .userId(userId)
                .totalAmount(totalAmount)
                .orderDate(LocalDateTime.now())
                .status("Pending")
                .build();
        order = orderDAO.save(order);

        for (Cart cart : cartItems) {
            Product product = productDAO.findById(cart.getProductId()).orElseThrow();
            OrderDetail detail = OrderDetail.builder()
                    .orderId(order.getOrderId())
                    .productId(cart.getProductId())
                    .quantity(cart.getQuantity())
                    .price(product.getPrice())
                    .build();
            orderDetailDAO.save(detail);
        }

        return order;
    }

    @Override
    public void updateStatus(Integer orderId, String status) {
        Order order = orderDAO.findById(orderId).orElseThrow();
        order.setStatus(status);
        orderDAO.save(order);
    }
}
