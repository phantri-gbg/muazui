package com.vinnet.controller;

import com.vinnet.model.User;
import com.vinnet.service.interfaces.FavoriteService;
import com.vinnet.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/favorites")
public class FavoriteController {
    @Autowired
    private FavoriteService favoriteService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String listFavorites(Model model, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        model.addAttribute("favorites", favoriteService.findByUserId(user.getUserId()));
        return "favorites/list";
    }

    @PostMapping("/add")
    public String addFavorite(@RequestParam Integer productId, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        favoriteService.addFavorite(user.getUserId(), productId);
        return "redirect:/products/" + productId;
    }

    @GetMapping("/remove/{productId}")
    public String removeFavorite(@PathVariable Integer productId, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        favoriteService.removeFavorite(user.getUserId(), productId);
        return "redirect:/favorites";
    }
}