package com.neobis.neotour.service;

import com.neobis.neotour.dto.BookingDto;
import com.neobis.neotour.dto.BookingInDto;
import org.springframework.data.domain.Page;

public interface BookingService {
    Page<BookingDto> getBookingsByTripId(int page, int size, Long tripId);

    BookingDto createBooking(BookingInDto bookingDto);

    void deleteBookingById(Long id);
}
