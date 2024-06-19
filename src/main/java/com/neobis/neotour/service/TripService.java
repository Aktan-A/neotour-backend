package com.neobis.neotour.service;

import com.neobis.neotour.dto.TripDto;
import com.neobis.neotour.dto.TripOutDto;
import com.neobis.neotour.enums.Continent;
import org.springframework.data.domain.Page;

public interface TripService {
    TripOutDto getTripById(Long id);
    Page<TripOutDto> getTrips(int page, int size, Continent continent);
    Page<TripOutDto> getPopularTrips(int page, int size);
    Page<TripOutDto> getFeaturedTrips(int page, int size);
    Page<TripOutDto> getMostVisitedTrips(int page, int size);
    Page<TripOutDto> getRecommendedTrips(int page, int size);
    TripDto createTrip(TripDto tripDto);
    TripDto updateTripById(Long id, TripDto tripDto);
    void deleteTripById(Long id);
}
