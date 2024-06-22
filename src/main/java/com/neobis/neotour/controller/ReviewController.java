package com.neobis.neotour.controller;

import com.neobis.neotour.dto.ReviewDto;
import com.neobis.neotour.dto.ReviewInDto;
import com.neobis.neotour.model.User;
import com.neobis.neotour.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ModelMapper modelMapper;

    @Operation(summary = "Create a review", description = "Returns details of the created review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully created")
    })
    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewInDto reviewInDto) {
        ReviewDto reviewDto = modelMapper.map(reviewInDto, ReviewDto.class);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        reviewDto.setUserId(user.getId());
        return ResponseEntity.ok(reviewService.createReview(reviewDto));
    }

    @Operation(summary = "Delete a review by id", description = "Deletes a review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Review id not found")
    })
    @DeleteMapping(path = "/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable("reviewId") Long id) {
        reviewService.deleteReviewById(id);
        return ResponseEntity.ok("Review successfully deleted.");
    }

}
