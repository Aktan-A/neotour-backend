package com.neobis.neotour.controller;

import com.neobis.neotour.dto.CountryDto;
import com.neobis.neotour.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping(path = "/{countryId}")
    public ResponseEntity<CountryDto> getCountryById(@PathVariable("countryId") Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @GetMapping
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return ResponseEntity.ok(countryService.getCountries());
    }

    @PostMapping
    public ResponseEntity<CountryDto> createCountry(@RequestBody CountryDto countryDto) {
        return ResponseEntity.ok(countryService.createCountry(countryDto));
    }

    @PutMapping(path = "/{countryId}")
    public ResponseEntity<CountryDto> updateCountryById(
            @PathVariable("countryId") Long id,
            @RequestBody CountryDto countryDto) {
        return ResponseEntity.ok(countryService.updateCountryById(id, countryDto));
    }

    @DeleteMapping(path = "/{countryId}")
    public ResponseEntity<String> deleteCountryById(@PathVariable("countryId") Long id) {
        countryService.deleteCountryById(id);
        return ResponseEntity.ok("Country successfully deleted.");
    }
}
