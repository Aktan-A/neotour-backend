package com.neobis.neotour.service;

import com.neobis.neotour.dto.TripDto;
import com.neobis.neotour.exceptions.ResourceNotFoundException;
import com.neobis.neotour.model.Image;
import com.neobis.neotour.model.Location;
import com.neobis.neotour.model.Trip;
import com.neobis.neotour.repository.ImageRepository;
import com.neobis.neotour.repository.LocationRepository;
import com.neobis.neotour.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;
    private final LocationRepository locationRepository;
    private final ImageRepository imageRepository;
    private final ModelMapper modelMapper;

    @Override
    public TripDto getTripById(Long id) {
        Optional<Trip> trip = tripRepository.findByIdAndDeletedFalse(id);
        if (trip.isEmpty()) {
            throw new ResourceNotFoundException("Trip with id " + id + " does not exist.");
        }
        return modelMapper.map(trip.get(), TripDto.class);
    }

    @Override
    public Page<TripDto> getTrips(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Trip> trips = tripRepository.findAllByDeletedFalse(pageRequest);
        Page<TripDto> tripDtos = trips.map(trip -> modelMapper.map(trip, TripDto.class));
        return tripDtos;
    }

    @Override
    public TripDto createTrip(TripDto tripDto) {
        Trip trip = modelMapper.map(tripDto, Trip.class);
        return modelMapper.map(tripRepository.save(trip), TripDto.class);
    }

    @Override
    public TripDto updateTripById(Long id, TripDto tripDto) {
        Optional<Trip> trip = tripRepository.findByIdAndDeletedFalse(id);
        if (trip.isEmpty()) {
            throw new ResourceNotFoundException("Trip with id " + id + " does not exist.");
        }
        Trip tripModel = trip.get();

        if (tripDto.getLocationId() != null) {
            Optional<Location> location = locationRepository.findByIdAndDeletedFalse(tripDto.getLocationId());
            if (location.isEmpty()) {
                throw new ResourceNotFoundException("Location with id " + id + " does not exist.");
            }
            tripModel.setLocation(location.get());
        }

        if (tripDto.getImageId() != null) {
            Optional<Image> image = imageRepository.findById(tripDto.getImageId());
            if (image.isEmpty()) {
                throw new ResourceNotFoundException("Image with id " + id + " does not exist.");
            }
            tripModel.setImage(image.get());
        }

        if (tripDto.getName() != null) {
            tripModel.setName(tripDto.getName());
        }
        if (tripDto.getDescription() != null) {
            tripModel.setDescription(tripDto.getDescription());
        }
        if (tripDto.getSeason() != null) {
            tripModel.setSeason(tripDto.getSeason());
        }
        if (tripDto.getMaxPeopleAmount() != null) {
            tripModel.setMaxPeopleAmount(tripDto.getMaxPeopleAmount());
        }
        if (tripDto.getFeatured() != null) {
            tripModel.setFeatured(tripDto.getFeatured());
        }

        return modelMapper.map(tripRepository.save(tripModel), TripDto.class);

    }

    @Override
    public void deleteTripById(Long id) {
        Optional<Trip> trip = tripRepository.findByIdAndDeletedFalse(id);
        if (trip.isEmpty()) {
            throw new ResourceNotFoundException("Trip with id " + id + " does not exist.");
        }
        Trip tripModel = trip.get();
        tripModel.setDeleted(true);
        tripRepository.save(tripModel);
    }
}
