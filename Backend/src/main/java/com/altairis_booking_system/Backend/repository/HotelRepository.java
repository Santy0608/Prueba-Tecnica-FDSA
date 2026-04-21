package com.altairis_booking_system.Backend.repository;

import com.altairis_booking_system.Backend.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface HotelRepository extends JpaRepository<Hotel, Long>, JpaSpecificationExecutor<Hotel> {

    @Query("SELECT h FROM Hotel h WHERE LOWER(h.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Hotel> findByNombreContaining(@Param("nombre") String nombre);


}
