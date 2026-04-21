package com.altairis_booking_system.Backend.repository;

import com.altairis_booking_system.Backend.domain.Disponibilidad;
import com.altairis_booking_system.Backend.domain.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DisponibilidadRepository extends JpaRepository<Disponibilidad, Long>, JpaSpecificationExecutor<TipoHabitacion> {

    @Query("SELECT d FROM Disponibilidad d WHERE d.tipoHabitacion.idTipoHabitacion = :tipoHabitacionId AND d.fecha = :fecha")
    Optional<Disponibilidad> findByTipoHabitacionIdAndFecha(Long tipoHabitacionId, LocalDate fecha);

    @Query("SELECT d FROM Disponibilidad d WHERE d.tipoHabitacion.idTipoHabitacion = :tipoHabitacionId AND d.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Disponibilidad> findByTipoHabitacionIdAndFechaBetween(
            @Param("tipoHabitacionId") Long tipoHabitacionId,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );

    @Query("SELECT d FROM Disponibilidad d WHERE d.fecha BETWEEN :fechaInicio AND :fechaFin")
    List<Disponibilidad> findByFechaBetween(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );

}

