package com.udc.topicos.parcial.ecobooking.domain;

import com.udc.topicos.parcial.ecobooking.ports.in.BookingUseCase;
import com.udc.topicos.parcial.ecobooking.ports.out.LoadBookingPort;
import com.udc.topicos.parcial.ecobooking.ports.out.SaveBookingPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements BookingUseCase {

    @Autowired
    private SaveBookingPort saveBookingPort;

    @Autowired
    private LoadBookingPort loadBookingPort;

    @Override
    public Booking createBooking(Booking booking) {
        return saveBookingPort.saveBooking(booking);
    }

    @Override
    public Booking getBooking(Long id) {
        return loadBookingPort.loadBooking(id);
    }
}