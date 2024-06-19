package com.neobis.neotour.service;

import com.neobis.neotour.dto.ReviewDto;
import com.neobis.neotour.exceptions.ResourceNotFoundException;
import com.neobis.neotour.model.Review;
import com.neobis.neotour.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<ReviewDto> getReviewsByTripId(int page, int size, Long tripId) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Review> reviews = reviewRepository.findAllByTripId(pageRequest, tripId);
        return reviews.map(review -> modelMapper.map(review, ReviewDto.class));
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        Review review = modelMapper.map(reviewDto, Review.class);
        return modelMapper.map(reviewRepository.save(review), ReviewDto.class);
    }

    @Override
    public void deleteReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isEmpty()) {
            throw new ResourceNotFoundException("Review with id " + id + " does not exist.");
        }
        reviewRepository.deleteById(id);
    }
}
