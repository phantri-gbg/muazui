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
    public String showLoginForm(HttpSession session) {
        // Nếu đã đăng nhập, chuyển hướng đến trang chủ
        if (session.getAttribute("userId") != null) {
            return "redirect:/";
        }
        return "login"; // Maps to login.html
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam String email, @RequestParam String password,
                               Model model, HttpSession session) {
        try {
            User user = userService.authenticate(email, password);
            if (user != null) {
                // Lưu thông tin người dùng vào session
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("email", user.getEmail());
                session.setAttribute("role", user.getRole());
                session.setMaxInactiveInterval(7 * 24 * 60 * 60); // 7 ngày
                return "redirect:/"; // Chuyển hướng đến trang chủ
            } else {
                model.addAttribute("error", "Email hoặc mật khẩu không đúng.");
                return "login";
            }
        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi khi đăng nhập. Vui lòng thử lại.");
            return "login";
        }
    }
}

