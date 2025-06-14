package com.vinnet.controller;

import com.vinnet.model.User;
import com.vinnet.service.interfaces.CartService;
import com.vinnet.service.interfaces.OrderService;
import com.vinnet.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/checkout")
    public String checkout(Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        orderService.createOrder(user.getUserId(), cartService.findByUserId(user.getUserId()));
        return "redirect:/order";
    }

    @GetMapping
    public String listOrders(Model model, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        model.addAttribute("orders", orderService.findByUserId(user.getUserId()));
        return "orders/list";
    }

    @GetMapping("/{id}")
    public String viewOrder(@PathVariable Integer id, Model model) {
        model.addAttribute("order", orderService.findById(id));
        return "orders/detail";
    }
}
