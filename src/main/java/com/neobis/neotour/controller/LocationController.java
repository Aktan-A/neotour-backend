package com.neobis.neotour.controller;

import com.neobis.neotour.dto.LocationDto;
import com.neobis.neotour.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        return ResponseEntity.ok(locationService.getLocations());
    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {
        return ResponseEntity.ok(locationService.createLocation(locationDto));
    }

    @PutMapping(path = "/{locationId}")
    public ResponseEntity<LocationDto> updateLocationById(
            @PathVariable("locationId") Long id,
            @RequestBody LocationDto locationDto) {
        return ResponseEntity.ok(locationService.updateLocationById(id, locationDto));
    }

    @DeleteMapping(path = "/{locationId}")
    public ResponseEntity<String> deleteLocationById(@PathVariable("locationId") Long id) {
        locationService.deleteLocationById(id);
        return ResponseEntity.ok("Location successfully deleted.");
    }

}
