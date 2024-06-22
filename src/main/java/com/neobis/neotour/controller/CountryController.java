package com.neobis.neotour.controller;

import com.neobis.neotour.dto.CountryDto;
import com.neobis.neotour.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @Operation(summary = "Get country details by id", description = "Returns country details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country details successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Country id not found")
    })
    @GetMapping(path = "/{countryId}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable("countryId") Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @Operation(summary = "Get all countries", description = "Returns a list of details for all created countries")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Countries successfully retrieved")
    })
    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return ResponseEntity.ok(countryService.getCountries());
    }

    @Operation(summary = "Create a country", description = "Returns details of the created country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country successfully created")
    })
    @PostMapping
    public ResponseEntity<CountryDto> createCountry(@RequestBody CountryDto countryDto) {
        return ResponseEntity.ok(countryService.createCountry(countryDto));
    }

    @Operation(summary = "Update a country by id", description = "Returns the updated details of the country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country successfully updated"),
            @ApiResponse(responseCode = "404", description = "Country id not found")
    })
    @PutMapping(path = "/{countryId}")
    public ResponseEntity<CountryDto> updateCountryById(
            @PathVariable("countryId") Long id,
            @RequestBody CountryDto countryDto) {
        return ResponseEntity.ok(countryService.updateCountryById(id, countryDto));
    }

    @Operation(summary = "Delete a country by id", description = "Deletes a country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Country id not found")
    })
    @DeleteMapping(path = "/{countryId}")
    public ResponseEntity<String> deleteCountryById(@PathVariable("countryId") Long id) {
        countryService.deleteCountryById(id);
        return ResponseEntity.ok("Country successfully deleted.");
    }
}
