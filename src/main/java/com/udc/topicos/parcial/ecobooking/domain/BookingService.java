package com.udc.topicos.parcial.ecobooking.domain;

import com.udc.topicos.parcial.ecobooking.exceptions.BookingException;
import com.udc.topicos.parcial.ecobooking.exceptions.BookingNotFoundException;
import com.udc.topicos.parcial.ecobooking.ports.in.BookingUseCase;
import com.udc.topicos.parcial.ecobooking.ports.out.LoadBookingPort;
import com.udc.topicos.parcial.ecobooking.ports.out.SaveBookingPort;
import com.udc.topicos.parcial.ecobooking.util.BookingSerializer;
import com.udc.topicos.parcial.ecobooking.util.BookingValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService implements BookingUseCase {

    @Autowired
    private SaveBookingPort saveBookingPort;

    @Autowired
    private LoadBookingPort loadBookingPort;

    @Autowired
    private BookingValidator bookingValidator; // Custom validator

    @Autowired
    private BookingSerializer bookingSerializer; // Custom serializer

    @Override
    public Booking createBooking(Booking booking) {

        bookingValidator.validate(booking);

        if (!isBookingAvailable(booking)) {
            throw new BookingException("Booking not available for the selected date and service");
        }
        booking.setConfirmed(false);
        return saveBookingPort.saveBooking(booking);
    }

    @Override
    public Booking getBooking(Long id) {
        Booking booking = loadBookingPort.loadBooking(id);
        if (booking == null) {
            throw new BookingNotFoundException("Booking with ID " + id + " not found");
        }
        return booking;
    }

    @Override
    public List<Booking> getAll() {
        return loadBookingPort.loadAllBooking();
    }

    private boolean isBookingAvailable(Booking booking) {
        List<Booking> bookings = loadBookingPort.loadAllBooking();
        return bookings.stream()
                .noneMatch(existingBooking ->
                        existingBooking.getServiceType().equals(booking.getServiceType()) &&
                                existingBooking.getDate().equals(booking.getDate()));
    }
}
