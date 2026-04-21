package com.altairis_booking_system.Backend.dto;

import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

public class ReservaDTO {

    private Long id;
    private String nombreCliente;
    private String emailCliente;
    private String telefonoCliente;
    private Date fechaCheckin;
    private Date fechaCheckout;
    private Integer cantidadHabitaciones;
    private BigDecimal precioTotal;
    private Enum estado;
    private String notas;
    private LocalDateTime createdAt;

    private Long tipoHabitacionId;
    private String nombreTipoHabitacion;



}
