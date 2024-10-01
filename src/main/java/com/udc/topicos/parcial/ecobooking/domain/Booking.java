package com.udc.topicos.parcial.ecobooking.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;



@Data
@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_seq")
    @SequenceGenerator(name = "booking_seq", sequenceName = "BOOKING_SEQ", allocationSize = 1)
    private Long id;

    @NotBlank(message = "Customer name cannot be blank")
    private String customerName;

    @NotBlank(message = "Service type cannot be blank")
    @Pattern(regexp = "lodging|tour", message = "Service type must be either 'lodging' or 'tour'")
    private String serviceType;

    @NotBlank(message = "Date cannot be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must be in format YYYY-MM-DD")
    private String date;

    @Min(value = 1, message = "Number of people must be at least 1")
    private int numberOfPeople;

    private boolean confirmed;
}
