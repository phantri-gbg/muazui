package com.vinnet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryID")
    private Integer categoryId;

    @Column(name = "Name", nullable = false, unique = true)
    private String name;
}