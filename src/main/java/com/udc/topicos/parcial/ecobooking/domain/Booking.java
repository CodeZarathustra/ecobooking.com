package com.udc.topicos.parcial.ecobooking.domain;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "BOOKING_SEQ", allocationSize = 1)
    private Long id;
    private String customerName;
    private String serviceType; // e.g., "lodging", "tour"
    private String date;
}
