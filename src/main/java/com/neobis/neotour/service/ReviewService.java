package com.neobis.neotour.service;

import com.neobis.neotour.dto.ReviewDto;
import org.springframework.data.domain.Page;

public interface ReviewService {
    Page<ReviewDto> getReviewsByTripId(int page, int size, Long tripId);
    ReviewDto createReview(ReviewDto reviewDto);
    void deleteReviewById(Long id);
}
