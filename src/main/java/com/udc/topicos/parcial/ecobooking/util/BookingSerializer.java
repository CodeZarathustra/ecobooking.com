package com.udc.topicos.parcial.ecobooking.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.udc.topicos.parcial.ecobooking.domain.Booking;
import com.udc.topicos.parcial.ecobooking.exceptions.BookingSerializationException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BookingSerializer {

    public String serialize(Booking booking) {
        // Serialización personalizada del objeto Booking a JSON, XML o cualquier formato deseado
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(booking);
        } catch (JsonProcessingException e) {
            throw new BookingSerializationException("Error serializing booking", e);
        }
    }

    public Booking deserialize(String data) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(data, Booking.class);
        } catch (IOException e) {
            throw new BookingSerializationException("Error deserializing booking", e);
        }
    }
}
