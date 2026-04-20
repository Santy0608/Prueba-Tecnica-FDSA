package com.altairis_booking_system.Backend.service;

import com.altairis_booking_system.Backend.domain.TipoHabitacion;
import com.altairis_booking_system.Backend.dto.TipoHabitacionDTO;

import java.util.List;
import java.util.Optional;

public interface TipoHabitacionService {

    List<TipoHabitacionDTO> listadoTiposHabitaciones();

    Optional<TipoHabitacionDTO> buscarTipoHabitacionPorId(long id);

    TipoHabitacionDTO guardarTipoHabitacion(TipoHabitacionDTO tipoHabitacionDTO);

    Optional<TipoHabitacionDTO> actualiarTipoHabitacion(TipoHabitacionDTO tipoHabitacionDTO, long id);

    void eliminarTipoHabitacionPorId(long id);

    TipoHabitacionDTO convertirADTO(TipoHabitacion tipoHabitacion);

}
