package com.altairis_booking_system.Backend.serviceImpl;

import com.altairis_booking_system.Backend.domain.Hotel;
import com.altairis_booking_system.Backend.dto.HotelDTO;
import com.altairis_booking_system.Backend.repository.HotelRepository;
import com.altairis_booking_system.Backend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    @Transactional(readOnly = true)
    public List<HotelDTO> listadoHoteles() {
        List<Hotel> hoteles = hotelRepository.findAll();
        List<HotelDTO> dtos = hoteles.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<HotelDTO> buscarHotelPorId(long idHotel) {
        return hotelRepository.findById(idHotel).map(this::convertirADTO);
    }

    @Override
    @Transactional
    public HotelDTO guardarHotel(HotelDTO hotelDTO) {
        Hotel hotel = new Hotel();
        hotel.setNombre(hotelDTO.getNombre());
        hotel.setDescripcion(hotelDTO.getDescripcion());
        hotel.setCiudad(hotelDTO.getCiudad());
        hotel.setPais(hotelDTO.getPais());
        hotel.setTelefono(hotelDTO.getTelefono());
        hotel.setEmail(hotelDTO.getEmail());
        hotel.setEstrellas(hotelDTO.getEstrellas());
        hotel.setActivo(hotelDTO.isActivo());
        hotel.setCreatedAt(LocalDateTime.now());
        Hotel hotelGuardado = hotelRepository.save(hotel);
        HotelDTO dto = new HotelDTO();
        dto.setNombre(hotelGuardado.getNombre());
        dto.setDescripcion(hotelGuardado.getDescripcion());
        dto.setCiudad(hotelGuardado.getCiudad());
        dto.setPais(hotelGuardado.getPais());
        dto.setTelefono(hotelGuardado.getTelefono());
        dto.setEmail(hotelGuardado.getEmail());
        dto.setEstrellas(hotelGuardado.getEstrellas());
        dto.setActivo(hotelGuardado.isActivo());
        dto.setCreated_at(hotelGuardado.getCreatedAt());
        return dto;
    }

    @Override
    @Transactional
    public Optional<HotelDTO> actualizarHotel(HotelDTO hotelDTO, long idHotel) {
        return hotelRepository.findById(idHotel).map(hotel -> {
            hotel.setNombre(hotelDTO.getNombre());
            hotel.setDescripcion(hotelDTO.getDescripcion());
            hotel.setCiudad(hotelDTO.getCiudad());
            hotel.setPais(hotelDTO.getPais());
            hotel.setTelefono(hotelDTO.getTelefono());
            hotel.setEmail(hotelDTO.getEmail());
            hotel.setEstrellas(hotelDTO.getEstrellas());
            hotel.setActivo(hotelDTO.isActivo());
            hotel.setCreatedAt(LocalDateTime.now());
            Hotel hotelActualizado = hotelRepository.save(hotel);
            return convertirADTO(hotelActualizado);
        });
    }

    @Override
    @Transactional
    public void eliminarHotelPorId(long idHotel) {
        hotelRepository.deleteById(idHotel);
    }

    @Override
    public HotelDTO convertirADTO(Hotel hotel) {
        HotelDTO dto = new HotelDTO();
        dto.setId(hotel.getId());
        dto.setNombre(hotel.getNombre());
        dto.setDescripcion(hotel.getDescripcion());
        dto.setCiudad(hotel.getCiudad());
        dto.setPais(hotel.getPais());
        dto.setTelefono(hotel.getTelefono());
        dto.setEmail(hotel.getEmail());
        dto.setEstrellas(hotel.getEstrellas());
        dto.setActivo(hotel.isActivo());
        dto.setCreated_at(hotel.getCreatedAt());
        return dto;
    }
}
