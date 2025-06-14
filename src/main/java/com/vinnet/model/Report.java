package com.vinnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ReportID")
    private Integer reportId;

    @Column(name = "ReporterID", nullable = false)
    private Integer reporterId;

    @Column(name = "ProductID", nullable = false)
    private Integer productId;

    @Column(name = "Reason", nullable = false)
    private String reason;

    @Column(name = "ReportDate")
    private LocalDateTime reportDate;
}
