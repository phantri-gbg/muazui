package com.vinnet.service.impl;

import com.vinnet.dao.ReviewDAO;
import com.vinnet.model.Review;
import com.vinnet.service.interfaces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewDAO reviewDAO;

    @Override
    public List<Review> findByProductId(Integer productId) {
        return reviewDAO.findByProductId(productId);
    }

    @Override
    public Review save(Review review) {
        return reviewDAO.save(review);
    }
}
