package com.neobis.neotour.controller;

import com.neobis.neotour.dto.LocationDto;
import com.neobis.neotour.service.LocationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    @Operation(summary = "Get all locations", description = "Returns a list of created locations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Locations successfully retrieved")
    })
    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        return ResponseEntity.ok(locationService.getLocations());
    }

    @Operation(summary = "Create a location", description = "Returns details of the created location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location successfully created"),
            @ApiResponse(responseCode = "404", description = "Country id not found")
    })
    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody LocationDto locationDto) {
        return ResponseEntity.ok(locationService.createLocation(locationDto));
    }

    @Operation(summary = "Update location by id", description = "Returns updated details of the location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location successfully updated"),
            @ApiResponse(responseCode = "404", description = "Location or country id not found")
    })
    @PutMapping(path = "/{locationId}")
    public ResponseEntity<LocationDto> updateLocationById(
            @PathVariable("locationId") Long id,
            @RequestBody LocationDto locationDto) {
        return ResponseEntity.ok(locationService.updateLocationById(id, locationDto));
    }

    @Operation(summary = "Delete a location by id", description = "Deletes a location")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Location id not found")
    })
    @DeleteMapping(path = "/{locationId}")
    public ResponseEntity<String> deleteLocationById(@PathVariable("locationId") Long id) {
        locationService.deleteLocationById(id);
        return ResponseEntity.ok("Location successfully deleted.");
    }

}
