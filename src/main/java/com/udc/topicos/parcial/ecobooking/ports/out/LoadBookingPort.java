package com.udc.topicos.parcial.ecobooking.ports.out;

import com.udc.topicos.parcial.ecobooking.domain.Booking;

public interface LoadBookingPort {
    Booking loadBooking(Long id);
}