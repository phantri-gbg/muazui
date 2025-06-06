package com.vinnet.controller;

import com.vinnet.model.Cart;
import com.vinnet.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Integer productId, @RequestParam Integer quantity, Authentication auth) {
        Integer userId = getUserId(auth);
        cartService.addToCart(userId, productId, quantity);
        return "redirect:/cart";
    }

    @GetMapping
    public String viewCart(Model model, Authentication auth) {
        Integer userId = getUserId(auth);
        model.addAttribute("cartItems", cartService.findByUserId(userId));
        return "cart/list";
    }

    private Integer getUserId(Authentication auth) {
        // Giả sử bạn có cách lấy userId từ auth
        return 1; // Thay bằng logic thực tế
    }
}