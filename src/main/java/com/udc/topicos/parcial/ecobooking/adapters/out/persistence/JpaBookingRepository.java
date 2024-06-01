package com.udc.topicos.parcial.ecobooking.adapters.out.persistence;

import com.udc.topicos.parcial.ecobooking.domain.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaBookingRepository extends JpaRepository<Booking, Long> {
}