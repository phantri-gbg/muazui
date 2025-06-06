package com.vinnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SecurityController {

    @GetMapping("/security/login/form")
    public String loginForm(Model model, @RequestParam(value = "error", required = false) String error) {
        model.addAttribute("msg", error != null ? "Sai email hoặc mật khẩu!" : "Vui lòng đăng nhập");
        return "security/login";
    }

    @GetMapping("/security/login/success")
    public String loginSuccess(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msg", "Đăng nhập thành công!");
        return "redirect:/home";
    }

    @GetMapping("/security/login/error")
    public String loginError(Model model) {
        model.addAttribute("msg", "Sai email hoặc mật khẩu!");
        return "security/login";
    }

    @GetMapping("/security/unauthorized")
    public String unauthorized(Model model) {
        model.addAttribute("msg", "Không có quyền truy cập!");
        return "security/login";
    }

    @GetMapping("/security/logout/success")
    public String logoutSuccess(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msg", "Bạn đã đăng xuất!");
        return "redirect:/home";
    }
}