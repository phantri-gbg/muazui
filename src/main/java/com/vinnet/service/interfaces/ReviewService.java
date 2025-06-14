package com.vinnet.service.interfaces;

import com.vinnet.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> findByProductId(Integer productId);
    Review save(Review review);
}
