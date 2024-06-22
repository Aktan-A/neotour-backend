package com.neobis.neotour.service;

import com.neobis.neotour.dto.ReviewDto;
import com.neobis.neotour.dto.ReviewInDto;
import com.neobis.neotour.exceptions.ResourceNotFoundException;
import com.neobis.neotour.model.Review;
import com.neobis.neotour.model.User;
import com.neobis.neotour.repository.ReviewRepository;
import com.neobis.neotour.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<ReviewDto> getReviewsByTripId(int page, int size, Long tripId) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Review> reviews = reviewRepository.findAllByTripId(pageRequest, tripId);
        return reviews.map(review -> modelMapper.map(review, ReviewDto.class));
    }

    @Override
    public ReviewDto createReview(ReviewInDto reviewDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Review review = modelMapper.map(reviewDto, Review.class);
        review.setUser(user);
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
