package com.udc.topicos.parcial.ecobooking.ports.in;

import com.udc.topicos.parcial.ecobooking.domain.Booking;

public interface BookingUseCase {
    Booking createBooking(Booking booking);
    Booking getBooking(Long id);
}