package com.neobis.neotour.controller;

import com.neobis.neotour.dto.TripDto;
import com.neobis.neotour.enums.Continent;
import com.neobis.neotour.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService tripService;

    @GetMapping(path = "/{tripId}")
    public ResponseEntity<TripDto> getTripById(@PathVariable("tripId") Long id) {
        return ResponseEntity.ok(tripService.getTripById(id));
    }

    @GetMapping
    public ResponseEntity<Page<TripDto>> getAllTrips(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(required = false) Continent continent) {
        return ResponseEntity.ok(tripService.getTrips(page, size, continent));
    }

    @GetMapping(path = "/popular")
    public ResponseEntity<Page<TripDto>> getPopularTrips(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(tripService.getPopularTrips(page, size));
    }

    @GetMapping(path = "/featured")
    public ResponseEntity<Page<TripDto>> getFeaturedTrips(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(tripService.getFeaturedTrips(page, size));
    }

    @GetMapping(path = "/most-visited")
    public ResponseEntity<Page<TripDto>> getMostVisitedTrips(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(tripService.getMostVisitedTrips(page, size));
    }

    @PostMapping
    public ResponseEntity<TripDto> createTrip(@RequestBody TripDto tripDto) {
        return ResponseEntity.ok(tripService.createTrip(tripDto));
    }

    @PutMapping(path = "/{tripId}")
    public ResponseEntity<TripDto> updateTripById(
            @PathVariable("tripId") Long id,
            @RequestBody TripDto tripDto) {
        return ResponseEntity.ok(tripService.updateTripById(id, tripDto));
    }

    @DeleteMapping(path = "/{tripId}")
    public ResponseEntity<String> deleteTripById(@PathVariable("tripId") Long id) {
        tripService.deleteTripById(id);
        return ResponseEntity.ok("Trip successfully deleted.");
    }

}
