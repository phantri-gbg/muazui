package com.vinnet.controller;

import com.vinnet.model.User;
import com.vinnet.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/security/register")
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "security/register";
    }

    @PostMapping
    public String register(User user, Model model) {
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("msg", "Email đã tồn tại!");
            return "security/register";
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPhone(" ");
        user.setAddress(" ");
        user.setProfilePicture(" ");
        user.setRole("User");
        user.setCreatedAt(LocalDateTime.now());
        user.setStatus(true);
        user.setIsSeller(false);
        userService.create(user);

        model.addAttribute("msg", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "security/login";
    }
}