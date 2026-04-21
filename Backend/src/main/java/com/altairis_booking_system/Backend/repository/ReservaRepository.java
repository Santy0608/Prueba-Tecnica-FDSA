package com.altairis_booking_system.Backend.repository;

import com.altairis_booking_system.Backend.domain.EstadoReserva;
import com.altairis_booking_system.Backend.domain.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long>, JpaSpecificationExecutor<Reserva> {



    @Query("SELECT r FROM Reserva r WHERE r.tipoHabitacion.idTipoHabitacion = :tipoHabitacionId")
    List<Reserva> findByTipoHabitacionId(Long tipoHabitacionId);





    @Query("SELECT r FROM Reserva r WHERE r.estado = :estado")
    List<Reserva> findByEstado(@Param("estado") EstadoReserva estado);

    @Query("SELECT r FROM Reserva r WHERE LOWER(r.nombreCliente) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Reserva> findByNombreClienteContaining(@Param("nombre") String nombre);

    @Query("SELECT r FROM Reserva r WHERE r.fechaCheckin BETWEEN :fechaInicio AND :fechaFin")
    List<Reserva> findByFechaCheckinBetween(
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );

    @Query("""
    SELECT r FROM Reserva r 
    WHERE (:estado IS NULL OR r.estado = :estado)
    AND (:nombre IS NULL OR LOWER(r.nombreCliente) LIKE LOWER(CONCAT('%', :nombre, '%')))
    AND (:fechaInicio IS NULL OR r.fechaCheckin >= :fechaInicio)
    AND (:fechaFin IS NULL OR r.fechaCheckin <= :fechaFin)
""")
    List<Reserva> filtrar(
            @Param("estado") EstadoReserva estado,
            @Param("nombre") String nombre,
            @Param("fechaInicio") LocalDate fechaInicio,
            @Param("fechaFin") LocalDate fechaFin
    );


}
