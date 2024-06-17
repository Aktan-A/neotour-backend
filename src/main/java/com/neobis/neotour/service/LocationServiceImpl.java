package com.neobis.neotour.service;

import com.neobis.neotour.dto.LocationDto;
import com.neobis.neotour.exceptions.ResourceNotFoundException;
import com.neobis.neotour.model.Country;
import com.neobis.neotour.model.Location;
import com.neobis.neotour.repository.CountryRepository;
import com.neobis.neotour.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<LocationDto> getLocations() {
        return locationRepository.findAllByDeletedFalse()
                .stream()
                .map(location -> modelMapper.map(location, LocationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LocationDto createLocation(LocationDto locationDto) {
        Location location = modelMapper.map(locationDto, Location.class);
        Optional<Country> country = countryRepository.findByIdAndDeletedFalse(locationDto.getCountryId());
        if (country.isEmpty()) {
            throw new ResourceNotFoundException("Country with id" + locationDto.getCountryId() + " does not exist.");
        }
        location.setCountry(country.get());
        return modelMapper.map(locationRepository.save(location), LocationDto.class);
    }

    @Override
    public LocationDto updateLocationById(Long id, LocationDto locationDto) {
        Optional<Location> location = locationRepository.findByIdAndDeletedFalse(id);
        if (location.isEmpty()) {
            throw new ResourceNotFoundException("Location with id" + id + " does not exist.");
        }
        Location locationModel = location.get();

        if (locationDto.getCountryId() != null) {
            Optional<Country> country = countryRepository.findByIdAndDeletedFalse(locationDto.getCountryId());
            if (country.isEmpty()) {
                throw new ResourceNotFoundException("Country with id" + locationDto.getCountryId() + " does not exist.");
            }
            locationModel.setCountry(country.get());
        }
        locationModel.setCity(locationDto.getCity());
        return modelMapper.map(locationRepository.save(locationModel), LocationDto.class);
    }

    @Override
    public void deleteLocationById(Long id) {
        Optional<Location> location = locationRepository.findByIdAndDeletedFalse(id);
        if (location.isEmpty()) {
            throw new ResourceNotFoundException("Location with id" + id + " does not exist.");
        }
        Location locationModel = location.get();
        locationModel.setDeleted(true);
        locationRepository.save(locationModel);
    }
}
