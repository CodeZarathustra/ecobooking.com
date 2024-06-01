package com.udc.topicos.parcial.ecobooking.ports.in;

import com.udc.topicos.parcial.ecobooking.domain.Booking;

import java.util.List;

public interface BookingUseCase {
    Booking createBooking(Booking booking);
    Booking getBooking(Long id);
    List<Booking> getAll();
}