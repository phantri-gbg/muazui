package com.vinnet.controller;

import com.vinnet.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping({"/", "/home"})
    public String home(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        boolean isAuthenticated = auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal());
        model.addAttribute("isAuthenticated", isAuthenticated);

        // Lấy danh sách sản phẩm
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("products", productService.findByTitleContainingIgnoreCase(keyword));
            model.addAttribute("keyword", keyword);
        } else {
            model.addAttribute("products", productService.findAll());
        }

        return "security/home";
    }
}
