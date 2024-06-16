package com.neobis.neotour.service;

import com.neobis.neotour.dto.CountryDto;
import com.neobis.neotour.exceptions.ResourceNotFoundException;
import com.neobis.neotour.model.Country;
import com.neobis.neotour.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CountryDto getCountryById(Long id) {
        Optional<Country> country = countryRepository.findByIdAndDeletedFalse(id);
        if (country.isEmpty()) {
            throw new ResourceNotFoundException("Country with id " + id + " does not exist.");
        }
        return modelMapper.map(country.get(), CountryDto.class);
    }

    @Override
    public List<CountryDto> getCountries() {
        return countryRepository.findAllByDeletedFalse()
                .stream()
                .map(country -> modelMapper.map(country, CountryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CountryDto createCountry(CountryDto countryDto) {
        Country country = modelMapper.map(countryDto, Country.class);
        return modelMapper.map(countryRepository.save(country), CountryDto.class);
    }

    @Override
    public CountryDto updateCountryById(Long id, CountryDto countryDto) {
        Optional<Country> country = countryRepository.findByIdAndDeletedFalse(id);
        if (country.isEmpty()) {
            throw new ResourceNotFoundException("Country with id " + id + " does not exist.");
        };
        Country countryModel = country.get();
        countryModel.setName(countryDto.getName());
        countryModel.setContinent(countryDto.getContinent());

        return modelMapper.map(countryRepository.save(countryModel), CountryDto.class);
    }

    @Override
    public void deleteCountryById(Long id) {
        Optional<Country> country = countryRepository.findByIdAndDeletedFalse(id);
        if (country.isEmpty()) {
            throw new ResourceNotFoundException("Country with id " + id + " does not exist.");
        };
        Country countryModel = country.get();
        countryModel.setDeleted(true);
        countryRepository.save(countryModel);
    }
}
