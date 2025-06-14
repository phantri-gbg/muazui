package com.vinnet.controller;

import com.vinnet.model.Message;
import com.vinnet.model.User;
import com.vinnet.service.interfaces.MessageService;
import com.vinnet.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listMessages(Model model, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        model.addAttribute("messages", messageService.findByUserId(user.getUserId()));
        return "messages/list";
    }

    @PostMapping
    public String sendMessage(@ModelAttribute Message message, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        message.setSenderId(user.getUserId());
        messageService.save(message);
        return "redirect:/messages";
    }
}
