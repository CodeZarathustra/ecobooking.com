package com.udc.topicos.parcial.ecobooking.exceptions;

public class BookingValidationException extends RuntimeException {
    public BookingValidationException(String message) {
        super(message);
    }
}