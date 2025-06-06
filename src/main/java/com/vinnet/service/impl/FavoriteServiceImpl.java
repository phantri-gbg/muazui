package com.vinnet.service.impl;

import com.vinnet.dao.FavoriteDAO;
import com.vinnet.model.Favorite;
import com.vinnet.service.interfaces.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteDAO favoriteDAO;

    @Override
    public List<Favorite> findByUserId(Integer userId) {
        return favoriteDAO.findByUserId(userId);
    }

    @Override
    public Favorite addFavorite(Integer userId, Integer productId) {
        Favorite existing = favoriteDAO.findByUserIdAndProductId(userId, productId);
        if (existing != null) {
            return existing;
        }
        Favorite favorite = Favorite.builder()
                .userId(userId)
                .productId(productId)
                .build();
        return favoriteDAO.save(favorite);
    }

    @Override
    public void removeFavorite(Integer userId, Integer productId) {
        Favorite favorite = favoriteDAO.findByUserIdAndProductId(userId, productId);
        if (favorite != null) {
            favoriteDAO.delete(favorite);
        }
    }
}