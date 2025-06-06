package com.vinnet.service.impl;

import com.vinnet.dao.ReportDAO;
import com.vinnet.model.Report;
import com.vinnet.service.interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportDAO reportDAO;

    @Override
    public List<Report> findByProductId(Integer productId) {
        return reportDAO.findByProductId(productId);
    }

    @Override
    public Report save(Report report) {
        return reportDAO.save(report);
    }

    @Override
    public List<Report> findAll() {
        return reportDAO.findAll();
    }
}