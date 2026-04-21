package com.altairis_booking_system.Backend.repository;

import com.altairis_booking_system.Backend.domain.EstadoReserva;
import com.altairis_booking_system.Backend.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long>, JpaSpecificationExecutor<Reserva> {


    List<Reserva> findByEstado(EstadoReserva estado);

    @Query("SELECT r FROM Reserva r WHERE r.tipoHabitacion.idTipoHabitacion = :tipoHabitacionId")
    List<Reserva> findByTipoHabitacionId(Long tipoHabitacionId);

    List<Reserva> findByFechaCheckinBetween(LocalDate inicio, LocalDate fin);

}
