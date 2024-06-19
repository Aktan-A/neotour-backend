package com.neobis.neotour.controller;

import com.neobis.neotour.dto.BookingDto;
import com.neobis.neotour.dto.BookingInDto;
import com.neobis.neotour.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingInDto bookingInDto) {
        BookingDto bookingDto = modelMapper.map(bookingInDto, BookingDto.class);
        return ResponseEntity.ok(bookingService.createBooking(bookingDto));
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBookingById(@PathVariable("bookingId") Long id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.ok("Booking successfully deleted.");
    }

}
