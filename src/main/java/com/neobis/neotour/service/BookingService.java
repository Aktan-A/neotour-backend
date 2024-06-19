package com.neobis.neotour.service;

import com.neobis.neotour.dto.BookingDto;
import org.springframework.data.domain.Page;

public interface BookingService {
    Page<BookingDto> getBookingsByTripId(int page, int size, Long tripId);
    BookingDto createBooking(BookingDto bookingDto);
    void deleteBookingById(Long id);
}
