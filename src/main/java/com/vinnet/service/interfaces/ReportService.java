package com.vinnet.service.interfaces;

import com.vinnet.model.Report;

import java.util.List;

public interface ReportService {
    List<Report> findByProductId(Integer productId);
    Report save(Report report);
    List<Report> findAll();
}
