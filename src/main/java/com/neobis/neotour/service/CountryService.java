package com.neobis.neotour.service;

import com.neobis.neotour.dto.CountryDto;

import java.util.List;

public interface CountryService {
    CountryDto getCountryById(Long id);
    List<CountryDto> getCountries();
    CountryDto createCountry(CountryDto countryDto);
    CountryDto updateCountryById(Long id, CountryDto countryDto);
    void deleteCountryById(Long id);

}
