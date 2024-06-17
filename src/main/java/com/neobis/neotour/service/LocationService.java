package com.neobis.neotour.service;

import com.neobis.neotour.dto.LocationDto;

import java.util.List;

public interface LocationService {
    List<LocationDto> getLocations();
    LocationDto createLocation(LocationDto locationDto);
    LocationDto updateLocationById(Long id, LocationDto locationDto);
    void deleteLocationById(Long id);
}
