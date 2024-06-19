package com.neobis.neotour.service;

import com.neobis.neotour.dto.BookingDto;
import com.neobis.neotour.exceptions.InvalidRequestException;
import com.neobis.neotour.exceptions.ResourceExistsException;
import com.neobis.neotour.exceptions.ResourceNotFoundException;
import com.neobis.neotour.model.Booking;
import com.neobis.neotour.model.Trip;
import com.neobis.neotour.repository.BookingRepository;
import com.neobis.neotour.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final TripRepository tripRepository;
    private final ModelMapper modelMapper;

    @Override
    public Page<BookingDto> getBookingsByTripId(int page, int size, Long tripId) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Booking> bookings = bookingRepository.findAllByDeletedFalseAndTripId(pageRequest, tripId);
        return bookings.map(booking -> modelMapper.map(booking, BookingDto.class));
    }

    @Override
    public BookingDto createBooking(BookingDto bookingDto) {
        boolean bookingExists = bookingRepository.existsByPhoneAndTripId(bookingDto.getPhone(), bookingDto.getTripId());
        if (bookingExists) {
            throw new ResourceExistsException("Booking for this phone and trip already exists.");
        }

        Optional<Trip> trip = tripRepository.findByIdAndDeletedFalse(bookingDto.getTripId());
        if (trip.isEmpty()) {
            throw new ResourceNotFoundException("Trip with id " + bookingDto.getTripId() + " does not exist.");
        }
        Trip tripModel = trip.get();

        Integer currentPeopleAmount = bookingRepository.sumPeopleAmountByTripId(bookingDto.getTripId());
        if (currentPeopleAmount + bookingDto.getPeopleAmount() > tripModel.getMaxPeopleAmount()) {
            throw new InvalidRequestException("Maximum amount of people has been reached.");
        }

        Booking booking = modelMapper.map(bookingDto, Booking.class);
        booking.setTrip(tripModel);

        return modelMapper.map(bookingRepository.save(booking), BookingDto.class);
    }

    @Override
    public void deleteBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findByIdAndDeletedFalse(id);
        if (booking.isEmpty()) {
            throw new ResourceNotFoundException("Booking with id " + id + " does not exist.");
        }
        Booking bookingModel = booking.get();
        bookingModel.setDeleted(true);
        bookingRepository.save(bookingModel);
    }
}
