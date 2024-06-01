package com.udc.topicos.parcial.ecobooking.ports.out;

import com.udc.topicos.parcial.ecobooking.domain.Booking;

import java.util.List;

public interface LoadBookingPort {
    Booking loadBooking(Long id);
    List<Booking> loadAllBooking();
}