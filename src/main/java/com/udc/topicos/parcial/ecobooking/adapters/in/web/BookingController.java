package com.udc.topicos.parcial.ecobooking.adapters.in.web;

import com.udc.topicos.parcial.ecobooking.domain.Booking;
import com.udc.topicos.parcial.ecobooking.ports.in.BookingUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingUseCase bookingUseCase;

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingUseCase.createBooking(booking);
    }

    @GetMapping("/{id}")
    public Booking getBooking(@PathVariable Long id) {
        return bookingUseCase.getBooking(id);
    }

    @GetMapping
    public List<Booking> getAllBooking() {
        return bookingUseCase.getAll();
    }
}