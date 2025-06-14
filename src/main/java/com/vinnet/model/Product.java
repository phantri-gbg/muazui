package com.vinnet.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Integer productId;

    @Column(name = "UserID", nullable = false)
    private Integer userId;

    @Column(name = "CategoryID")
    private Integer categoryId;

    @Column(name = "Title", nullable = false)
    private String title;

    @Column(name = "Description")
    private String description;

    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @Column(name = "Quantity", nullable = false)
    private Integer quantity;

    @Column(name = "IsAvailable")
    private Boolean isAvailable;

    @Column(name = "ImageUrl")
    private String imageUrl;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}
