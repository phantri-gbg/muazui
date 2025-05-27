package com.vinnet.controller;

import com.vinnet.model.User;
import com.vinnet.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.regex.Pattern;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Trả về tên template Thymeleaf (register.html)
    }

    @PostMapping("/register")
    public String processRegister(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            @RequestParam(required = false) String fullname,
            Model model,
            RedirectAttributes redirectAttributes) {

        // Kiểm tra định dạng email
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!Pattern.matches(emailRegex, email)) {
            model.addAttribute("error", "Email không hợp lệ.");
            return "register";
        }

        // Kiểm tra mật khẩu và xác nhận mật khẩu
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "Mật khẩu xác nhận không khớp.");
            return "register";
        }

        // Kiểm tra độ dài mật khẩu
        if (password.length() < 6) {
            model.addAttribute("error", "Mật khẩu phải có ít nhất 6 ký tự.");
            return "register";
        }

        // Kiểm tra email đã tồn tại
        if (userRepository.findByEmail(email) != null) {
            model.addAttribute("error", "Email đã được sử dụng.");
            return "register";
        }

        // Mã hóa mật khẩu
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Tạo user_id mới
        Integer maxUserId = userRepository.findMaxUserId();
        int newUserId = (maxUserId == null) ? 1 : maxUserId + 1;

        // Tạo và lưu người dùng mới
        User user = new User();
        user.setUserId(newUserId);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        user.setName(fullname != null && !fullname.trim().isEmpty() ? fullname : null); // Lưu NULL nếu fullname rỗng
        user.setRole("user");
        user.setPoints(0);
        user.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        // Thêm thông báo thành công vào flash attributes
        redirectAttributes.addFlashAttribute("success", "Đăng ký thành công! Vui lòng đăng nhập.");
        return "redirect:/login"; // Chuyển hướng đến trang đăng nhập
    }
}