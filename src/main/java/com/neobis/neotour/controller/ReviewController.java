package com.neobis.neotour.controller;

import com.neobis.neotour.dto.ReviewDto;
import com.neobis.neotour.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.createReview(reviewDto));
    }

    @DeleteMapping(path = "/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable("reviewId") Long id) {
        reviewService.deleteReviewById(id);
        return ResponseEntity.ok("Review successfully deleted.");
    }

}
