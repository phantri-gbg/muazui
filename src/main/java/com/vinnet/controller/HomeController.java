package com.vinnet.controller;

import com.vinnet.model.Product;
import com.vinnet.model.User;
import com.vinnet.repository.ProductRepository;
import com.vinnet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping({"/", "/home"})
    public String showHomePage(Model model, HttpSession session, @RequestParam(value = "search", required = false) String search) {
        List<Product> products;
        if (search != null && !search.trim().isEmpty()) {
            products = productRepository.findByKeyword(search);
        } else {
            products = productRepository.findAll();
        }
        products.forEach(product -> {
            // Xử lý hình ảnh
            if (product.getImages() != null) {
                String[] imageLinks = product.getImages().split(",");
                if (imageLinks.length > 0 && !imageLinks[0].matches(".*\\.(jpg|png|jpeg|gif)$")) {
                    product.setImages("https://via.placeholder.com/150?text=Invalid+Image");
                }
            } else {
                product.setImages("https://via.placeholder.com/150?text=No+Image");
            }
            // Xử lý tên người bán
            User seller = userRepository.findById(product.getUserId()).orElse(null);
            product.setSellerName(seller != null && seller.getName() != null ? seller.getName() : "Unknown");
        });
        model.addAttribute("products", products);
        model.addAttribute("search", search);
        // Truyền userId từ session để hiển thị trạng thái đăng nhập
        model.addAttribute("session.userId", session.getAttribute("userId"));
        return "home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}