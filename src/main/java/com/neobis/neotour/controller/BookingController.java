package com.neobis.neotour.controller;

import com.neobis.neotour.dto.BookingDto;
import com.neobis.neotour.dto.BookingInDto;
import com.neobis.neotour.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Create a new booking", description = "Returns the created booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking successfully created"),
            @ApiResponse(responseCode = "400",
                    description = "Booking for this trip or phone already exists. " +
                            "Maximum amount of people has been reached"),
            @ApiResponse(responseCode = "404", description = "Trip id not found")
    })
    @PostMapping
    public ResponseEntity<BookingDto> createBooking(@RequestBody BookingInDto bookingInDto) {
        return ResponseEntity.ok(bookingService.createBooking(bookingInDto));
    }

    @Operation(summary = "Delete a booking by id", description = "Deletes a booking")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Booking successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Booking id not found")
    })
    @DeleteMapping("/{bookingId}")
    public ResponseEntity<String> deleteBookingById(@PathVariable("bookingId") Long id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.ok("Booking successfully deleted.");
    }

}
