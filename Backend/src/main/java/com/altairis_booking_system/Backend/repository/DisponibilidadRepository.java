package com.altairis_booking_system.Backend.repository;

import com.altairis_booking_system.Backend.domain.Disponibilidad;
import com.altairis_booking_system.Backend.domain.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.Optional;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long>, JpaSpecificationExecutor<TipoHabitacion> {

    Optional<Disponibilidad> findByTipoHabitacionIdAndFecha(Long tipoHabitacionId, LocalDate fecha);

}

