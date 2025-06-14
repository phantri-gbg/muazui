package com.vinnet.controller;

import com.vinnet.model.Review;
import com.vinnet.model.User;
import com.vinnet.service.interfaces.ReviewService;
import com.vinnet.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @GetMapping("/new/{productId}")
    public String newReviewForm(@PathVariable Integer productId, Model model) {
        Review review = new Review();
        review.setProductId(productId);
        model.addAttribute("review", review);
        return "reviews/form";
    }

    @PostMapping
    public String saveReview(@ModelAttribute Review review, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        review.setUserId(user.getUserId());
        reviewService.save(review);
        return "redirect:/products/" + review.getProductId();
    }
}
