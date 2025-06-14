package com.vinnet.dao;

import com.vinnet.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteDAO extends JpaRepository<Favorite, Integer> {
    List<Favorite> findByUserId(Integer userId);
    Favorite findByUserIdAndProductId(Integer userId, Integer productId);
}
