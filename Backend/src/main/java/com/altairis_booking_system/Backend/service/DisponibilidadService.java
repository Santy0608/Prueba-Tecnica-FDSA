package com.altairis_booking_system.Backend.service;

import com.altairis_booking_system.Backend.domain.Disponibilidad;
import com.altairis_booking_system.Backend.dto.DisponibilidadDTO;

import java.util.List;
import java.util.Optional;

public interface DisponibilidadService {

    List<DisponibilidadDTO> listadoDisponibilidades();

    Optional<DisponibilidadDTO> buscarDisponibilidadPorId(long id);

    DisponibilidadDTO guardarDisponibilidad(DisponibilidadDTO disponibilidadDTO);

    Optional<DisponibilidadDTO> actualizarDisponibilidad(DisponibilidadDTO disponibilidadDTO, long id);

    DisponibilidadDTO convertirADTO(Disponibilidad disponibilidad);

}
