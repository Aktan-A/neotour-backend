package com.neobis.neotour.service;

import com.neobis.neotour.dto.TripDto;
import com.neobis.neotour.enums.Continent;
import org.springframework.data.domain.Page;

public interface TripService {
    TripDto getTripById(Long id);
    Page<TripDto> getTrips(int page, int size, Continent continent);
    Page<TripDto> getPopularTrips(int page, int size);
    Page<TripDto> getFeaturedTrips(int page, int size);
    Page<TripDto> getMostVisitedTrips(int page, int size);
    TripDto createTrip(TripDto tripDto);
    TripDto updateTripById(Long id, TripDto tripDto);
    void deleteTripById(Long id);
}
