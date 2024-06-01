package com.udc.topicos.parcial.ecobooking.adapters.out.persistence;

import com.udc.topicos.parcial.ecobooking.domain.Booking;
import com.udc.topicos.parcial.ecobooking.ports.out.LoadBookingPort;
import com.udc.topicos.parcial.ecobooking.ports.out.SaveBookingPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class BookingRepository implements SaveBookingPort, LoadBookingPort {

    @Autowired
    private JpaBookingRepository jpaBookingRepository;

    @Override
    public Booking saveBooking(Booking booking) {
        return jpaBookingRepository.save(booking);
    }

    @Override
    public Booking loadBooking(Long id) {
        return jpaBookingRepository.findById(id).orElse(null);
    }
}