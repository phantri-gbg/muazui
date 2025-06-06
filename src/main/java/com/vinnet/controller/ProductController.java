package com.vinnet.controller;

import com.vinnet.model.Product;
import com.vinnet.service.interfaces.CategoryService;
import com.vinnet.service.interfaces.ProductService;
import com.vinnet.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.findAll());
        return "products/list";
    }

    @GetMapping("/{id}")
    public String viewProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.findById(id).orElseThrow());
        model.addAttribute("reviews", reviewService.findByProductId(id));
        return "products/detail";
    }

    @GetMapping("/seller/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAll());
        return "products/seller/form";
    }

    @PostMapping("/seller")
    public String saveProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/seller/edit/{id}")
    public String editProductForm(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.findById(id).orElseThrow());
        model.addAttribute("categories", categoryService.findAll());
        return "products/seller/form";
    }

    @GetMapping("/seller/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/products";
    }
}