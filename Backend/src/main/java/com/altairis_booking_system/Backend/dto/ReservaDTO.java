package com.altairis_booking_system.Backend.dto;

import com.altairis_booking_system.Backend.domain.EstadoReserva;
import jakarta.persistence.Column;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ReservaDTO {

    private Long id;
    private String nombreCliente;
    private String emailCliente;
    private String telefonoCliente;
    private LocalDate fechaCheckin;
    private LocalDate fechaCheckout;
    private Integer cantidadHabitaciones;
    private BigDecimal precioTotal;
    private EstadoReserva estado;
    private String notas;
    private LocalDateTime createdAt;

    private Long tipoHabitacionId;
    private String nombreTipoHabitacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public LocalDate getFechaCheckin() {
        return fechaCheckin;
    }

    public void setFechaCheckin(LocalDate fechaCheckin) {
        this.fechaCheckin = fechaCheckin;
    }

    public LocalDate getFechaCheckout() {
        return fechaCheckout;
    }

    public void setFechaCheckout(LocalDate fechaCheckout) {
        this.fechaCheckout = fechaCheckout;
    }

    public Integer getCantidadHabitaciones() {
        return cantidadHabitaciones;
    }

    public void setCantidadHabitaciones(Integer cantidadHabitaciones) {
        this.cantidadHabitaciones = cantidadHabitaciones;
    }

    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(BigDecimal precioTotal) {
        this.precioTotal = precioTotal;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getTipoHabitacionId() {
        return tipoHabitacionId;
    }

    public void setTipoHabitacionId(Long tipoHabitacionId) {
        this.tipoHabitacionId = tipoHabitacionId;
    }

    public String getNombreTipoHabitacion() {
        return nombreTipoHabitacion;
    }

    public void setNombreTipoHabitacion(String nombreTipoHabitacion) {
        this.nombreTipoHabitacion = nombreTipoHabitacion;
    }
}
