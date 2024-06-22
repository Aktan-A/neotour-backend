package com.neobis.neotour.controller;

import com.neobis.neotour.dto.BookingDto;
import com.neobis.neotour.dto.ReviewDto;
import com.neobis.neotour.dto.TripDto;
import com.neobis.neotour.dto.TripOutDto;
import com.neobis.neotour.enums.Continent;
import com.neobis.neotour.service.BookingService;
import com.neobis.neotour.service.ReviewService;
import com.neobis.neotour.service.TripService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;
    private final ReviewService reviewService;
    private final BookingService bookingService;

    @Operation(summary = "Get trip by id", description = "Returns trip details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trip successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Trip id not found")
    })
    @GetMapping(path = "/{tripId}")
    public ResponseEntity<TripOutDto> getTripById(@PathVariable("tripId") Long id) {
        return ResponseEntity.ok(tripService.getTripById(id));
    }

    @Operation(summary = "Get all trips with pagination", description = "Returns a list of all created trips")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trips successfully retrieved")
    })
    @GetMapping
    public ResponseEntity<Page<TripOutDto>> getAllTrips(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) Continent continent) {
        return ResponseEntity.ok(tripService.getTrips(page, size, continent));
    }

    @Operation(summary = "Get all trips sorted by popularity with pagination",
            description = "Returns a list of all trips sorted by their page visits")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trips successfully retrieved")
    })
    @GetMapping(path = "/popular")
    public ResponseEntity<Page<TripOutDto>> getPopularTrips(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(tripService.getPopularTrips(page, size));
    }

    @Operation(summary = "Get all featured trips with pagination",
            description = "Returns a list of all trips marked as featured")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trips successfully retrieved")
    })
    @GetMapping(path = "/featured")
    public ResponseEntity<Page<TripOutDto>> getFeaturedTrips(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(tripService.getFeaturedTrips(page, size));
    }

    @Operation(summary = "Get all trips sorted by bookings with pagination",
            description = "Returns a list of all trips sorted by the amount of bookings they have")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trips successfully retrieved")
    })
    @GetMapping(path = "/most-visited")
    public ResponseEntity<Page<TripOutDto>> getMostVisitedTrips(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(tripService.getMostVisitedTrips(page, size));
    }

    @Operation(summary = "Get all recommended trips with pagination",
            description = "Returns a list of all trips for the current season of the year")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trips successfully retrieved")
    })
    @GetMapping(path = "/recommended")
    public ResponseEntity<Page<TripOutDto>> getRecommendedTrips(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(tripService.getRecommendedTrips(page, size));
    }

    @Operation(summary = "Get all reviews for the trip with pagination",
            description = "Returns a list of reviews for the trip")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reviews successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Trip id not found")
    })
    @GetMapping(path = "/{tripId}/reviews")
    public ResponseEntity<Page<ReviewDto>> getTripReviews(
            @PathVariable("tripId") Long id,
            @RequestParam int page,
            @RequestParam int size) {
        return ResponseEntity.ok(reviewService.getReviewsByTripId(page, size, id));
    }

    @Operation(summary = "Get all bookings for the trip with pagination",
            description = "Returns a list of bookings for the trip")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Bookings successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Trip id not found")
    })
    @GetMapping(path = "/{tripId}/bookings")
    public ResponseEntity<Page<BookingDto>> getAllBookingsByTripId(
            @PathVariable("tripId") Long id,
            @RequestParam int page,
            @RequestParam int size) {
        return ResponseEntity.ok(bookingService.getBookingsByTripId(page, size, id));
    }

    @Operation(summary = "Create a trip", description = "Returns details of the created trip")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trip successfully created"),
    })
    @PostMapping
    public ResponseEntity<TripDto> createTrip(@RequestBody TripDto tripDto) {
        return ResponseEntity.ok(tripService.createTrip(tripDto));
    }

    @Operation(summary = "Update a trip by id", description = "Returns updated details of a trip")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trip successfully updated"),
            @ApiResponse(responseCode = "404", description = "Trip, image or location id not found")
    })
    @PutMapping(path = "/{tripId}")
    public ResponseEntity<TripDto> updateTripById(
            @PathVariable("tripId") Long id,
            @RequestBody TripDto tripDto) {
        return ResponseEntity.ok(tripService.updateTripById(id, tripDto));
    }

    @Operation(summary = "Delete a trip by id", description = "Deletes a trip")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Trip successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Trip id not found")
    })
    @DeleteMapping(path = "/{tripId}")
    public ResponseEntity<String> deleteTripById(@PathVariable("tripId") Long id) {
        tripService.deleteTripById(id);
        return ResponseEntity.ok("Trip successfully deleted.");
    }

}
