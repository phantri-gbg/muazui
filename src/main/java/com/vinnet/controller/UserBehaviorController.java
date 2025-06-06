package com.vinnet.controller;

import com.vinnet.service.interfaces.UserBehaviorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/behaviors")
public class UserBehaviorController {
    @Autowired
    private UserBehaviorService userBehaviorService;

    @GetMapping
    public String listBehaviors(Model model) {
        model.addAttribute("behaviors", userBehaviorService.findAll());
        return "behaviors/list";
    }
}