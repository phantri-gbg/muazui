package com.vinnet.dao;

import com.vinnet.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportDAO extends JpaRepository<Report, Integer> {
    List<Report> findByProductId(Integer productId);
}