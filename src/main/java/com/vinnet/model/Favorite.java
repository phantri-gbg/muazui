package com.vinnet.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Favorites")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FavoriteID")
    private Integer favoriteId;

    @Column(name = "UserID", nullable = false)
    private Integer userId;

    @Column(name = "ProductID", nullable = false)
    private Integer productId;
}
