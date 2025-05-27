package com.vinnet.controller;

import com.vinnet.model.User;
import com.vinnet.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Maps to login.html
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password,
                               Model model, HttpSession session) {
        try {
            User user = userService.authenticate(email, password);
            if (user != null) {
                // Store user info in session
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("role", user.getRole());
                session.setMaxInactiveInterval(7 * 24 * 60 * 60); // 7 days
                return "redirect:/"; // Redirect to homepage
            } else {
                model.addAttribute("error", "Invalid email or password");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during login. Please try again.");
            return "login";
        }
    }
}