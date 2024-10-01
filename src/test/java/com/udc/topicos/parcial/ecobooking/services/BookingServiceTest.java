package com.udc.topicos.parcial.ecobooking.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.udc.topicos.parcial.ecobooking.domain.Booking;
import com.udc.topicos.parcial.ecobooking.domain.BookingService;
import com.udc.topicos.parcial.ecobooking.exceptions.BookingNotFoundException;
import com.udc.topicos.parcial.ecobooking.ports.out.LoadBookingPort;
import com.udc.topicos.parcial.ecobooking.ports.out.SaveBookingPort;
import com.udc.topicos.parcial.ecobooking.util.BookingValidator;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Set;

public class BookingServiceTest {

    @Mock
    private SaveBookingPort saveBookingPort;
    @Mock
    private LoadBookingPort loadBookingPort;
    @Mock
    private BookingValidator bookingValidator;

    @InjectMocks
    private BookingService bookingService;
    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear el validador para las anotaciones @NotBlank, @Min, etc.
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testCreateBookingSuccessfully() {
        Booking booking = new Booking();
        booking.setCustomerName("John Doe");
        booking.setServiceType("lodging");
        booking.setDate("2024-10-01");

        when(saveBookingPort.saveBooking(booking)).thenReturn(booking);
        doNothing().when(bookingValidator).validate(booking);
        Booking createdBooking = bookingService.createBooking(booking);

        assertNotNull(createdBooking);
        assertEquals("John Doe", createdBooking.getCustomerName());
        verify(saveBookingPort, times(1)).saveBooking(booking);
    }

    @Test
    void testCreateBookingWithInvalidCustomerName() {
        Booking booking = new Booking();
        booking.setCustomerName(""); // Inválido
        booking.setServiceType("lodging");
        booking.setDate("2024-10-01");

        Set<ConstraintViolation<Booking>> violations = validator.validate(booking);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Customer name cannot be blank")));
    }

    @Test
    void testCreateBookingWithInvalidServiceType() {
        Booking booking = new Booking();
        booking.setCustomerName("John Doe");
        booking.setServiceType(""); // Inválido
        booking.setDate("2024-10-01");
        Set<ConstraintViolation<Booking>> violations = validator.validate(booking);
        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Service type cannot be blank")));
    }

    @Test
    void testGetBookingSuccessfully() {
        Long bookingId = 1L;
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setCustomerName("John Doe");

        when(loadBookingPort.loadBooking(bookingId)).thenReturn(booking);

        Booking foundBooking = bookingService.getBooking(bookingId);

        assertNotNull(foundBooking);
        assertEquals(bookingId, foundBooking.getId());
        verify(loadBookingPort, times(1)).loadBooking(bookingId);
    }

    @Test
    void testGetAllBookings() {
        Booking booking1 = new Booking();
        booking1.setId(1L);
        booking1.setCustomerName("John Doe");

        Booking booking2 = new Booking();
        booking2.setId(2L);
        booking2.setCustomerName("Jane Smith");

        List<Booking> bookings = List.of(booking1, booking2);

        when(loadBookingPort.loadAllBooking()).thenReturn(bookings);

        List<Booking> foundBookings = bookingService.getAll();

        assertNotNull(foundBookings);
        assertEquals(2, foundBookings.size());
        verify(loadBookingPort, times(1)).loadAllBooking();
    }

    @Test
    void testCreateBookingWithInvalidDate() {
        Booking booking = new Booking();
        booking.setCustomerName("John Doe");
        booking.setServiceType("tour");
        booking.setDate(""); // Inválido

        Set<ConstraintViolation<Booking>> violations = validator.validate(booking);

        assertFalse(violations.isEmpty());
        assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains("Date must be in format YYYY-MM-DD")));
    }

    @Test
    void testGetBookingThrowsBookingNotFoundException() {
        Long bookingId = 1L;

        when(loadBookingPort.loadBooking(bookingId)).thenReturn(null);

        BookingNotFoundException exception = assertThrows(BookingNotFoundException.class, () ->
            bookingService.getBooking(bookingId));

        assertEquals("Booking with ID " + bookingId + " not found", exception.getMessage());
        verify(loadBookingPort, times(1)).loadBooking(bookingId);
    }

}
