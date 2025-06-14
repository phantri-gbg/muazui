package com.vinnet.controller;

import com.vinnet.model.Report;
import com.vinnet.model.User;
import com.vinnet.service.interfaces.ReportService;
import com.vinnet.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private ReportService reportService;

    @Autowired
    private UserService userService;

    @GetMapping("/new/{productId}")
    public String newReportForm(@PathVariable Integer productId, Model model) {
        Report report = new Report();
        report.setProductId(productId);
        model.addAttribute("report", report);
        return "reports/form";
    }

    @PostMapping
    public String saveReport(@ModelAttribute Report report, Authentication auth) {
        User user = userService.findByEmail(auth.getName()).orElseThrow();
        report.setReporterId(user.getUserId());
        reportService.save(report);
        return "redirect:/products/" + report.getProductId();
    }

    @GetMapping("/admin")
    public String listReports(Model model) {
        model.addAttribute("reports", reportService.findAll());
        return "reports/admin/list";
    }
}
