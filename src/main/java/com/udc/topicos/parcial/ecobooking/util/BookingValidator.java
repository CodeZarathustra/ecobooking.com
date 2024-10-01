package com.udc.topicos.parcial.ecobooking.util;

import com.udc.topicos.parcial.ecobooking.domain.Booking;
import com.udc.topicos.parcial.ecobooking.exceptions.BookingValidationException;
import com.udc.topicos.parcial.ecobooking.exceptions.InvalidBookingException;
import org.springframework.stereotype.Component;

@Component
public class BookingValidator {

    public void validate(Booking booking) {
        if (booking.getNumberOfPeople() <= 0) {
            throw new BookingValidationException("The number of people must be greater than 0");
        }
        if (booking.getCustomerName().isEmpty()) {
            throw new InvalidBookingException("Customer name cannot be blank.");
        }
    }
}
