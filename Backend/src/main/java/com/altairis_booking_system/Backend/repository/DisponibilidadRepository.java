package com.altairis_booking_system.Backend.repository;

import com.altairis_booking_system.Backend.domain.Disponibilidad;
import com.altairis_booking_system.Backend.domain.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long>, JpaSpecificationExecutor<TipoHabitacion> {

}

