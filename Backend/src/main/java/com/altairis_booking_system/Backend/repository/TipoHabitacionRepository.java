package com.altairis_booking_system.Backend.repository;

import com.altairis_booking_system.Backend.domain.TipoHabitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Long>, JpaSpecificationExecutor<TipoHabitacion> {

    @Query("SELECT t FROM TipoHabitacion t WHERE LOWER(t.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<TipoHabitacion> findByNombreContaining(@Param("nombre") String nombre);

    @Query("SELECT t FROM TipoHabitacion t WHERE t.hotel.idHotel = :hotelId")
    List<TipoHabitacion> findByHotelId(@Param("hotelId") Long hotelId);

}

