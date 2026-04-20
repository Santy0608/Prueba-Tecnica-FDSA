package com.altairis_booking_system.Backend.service;

import com.altairis_booking_system.Backend.domain.Hotel;
import com.altairis_booking_system.Backend.dto.HotelDTO;

import java.util.List;
import java.util.Optional;

public interface HotelService {

    List<HotelDTO> listadoHoteles();

    Optional<HotelDTO> buscarHotelPorId(long idHotel);

    HotelDTO guardarHotel(HotelDTO hotelDTO);

    Optional<HotelDTO> actualizarHotel(HotelDTO hotelDTO, long idHotel);

    void eliminarHotelPorId(long idHotel);

    HotelDTO convertirADTO(Hotel hotel);


}

