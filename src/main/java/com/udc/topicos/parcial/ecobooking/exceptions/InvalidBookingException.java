package com.udc.topicos.parcial.ecobooking.exceptions;

public class InvalidBookingException extends RuntimeException {
    public InvalidBookingException(String message) {
        super(message);
    }
}
