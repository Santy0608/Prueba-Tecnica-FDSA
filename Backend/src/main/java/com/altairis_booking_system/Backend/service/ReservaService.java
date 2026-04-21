package com.altairis_booking_system.Backend.service;

import com.altairis_booking_system.Backend.domain.EstadoReserva;
import com.altairis_booking_system.Backend.domain.Reserva;
import com.altairis_booking_system.Backend.dto.ReservaDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservaService {

    List<ReservaDTO> listadoReservas();

    Optional<ReservaDTO> buscarReservaPorId(long id);

    ReservaDTO crearReserva(ReservaDTO reservaDTO);

    ReservaDTO actualizarReserva(ReservaDTO reservaDTO, long id);

    void actualizarEstado(Long id, EstadoReserva estado);

    void eliminarReservaPorId(Long id);

    List<ReservaDTO> buscarPorEstado(EstadoReserva estado);

    List<ReservaDTO> buscarPorFechas(LocalDate inicio, LocalDate fin);

    ReservaDTO convertirADTO(Reserva reserva);

    List<ReservaDTO> filtrar(EstadoReserva estado, String nombre, LocalDate fechaInicio, LocalDate fechaFin);
}
