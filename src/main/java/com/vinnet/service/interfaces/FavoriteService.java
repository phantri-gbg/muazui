package com.vinnet.service.interfaces;

import com.vinnet.model.Favorite;

import java.util.List;

public interface FavoriteService {
    List<Favorite> findByUserId(Integer userId);
    Favorite addFavorite(Integer userId, Integer productId);
    void removeFavorite(Integer userId, Integer productId);
}