package com.altairis_booking_system.Backend.serviceImpl;

import com.altairis_booking_system.Backend.domain.TipoHabitacion;
import com.altairis_booking_system.Backend.dto.TipoHabitacionDTO;
import com.altairis_booking_system.Backend.repository.HotelRepository;
import com.altairis_booking_system.Backend.repository.TipoHabitacionRepository;
import com.altairis_booking_system.Backend.service.TipoHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TipoHabitacionServiceImpl implements TipoHabitacionService {

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TipoHabitacionDTO> listadoTiposHabitaciones() {
        List<TipoHabitacion> tiposHabitaciones = tipoHabitacionRepository.findAll();
        List<TipoHabitacionDTO> dtos = tiposHabitaciones.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TipoHabitacionDTO> buscarTipoHabitacionPorId(long id) {
        return tipoHabitacionRepository.findById(id).map(this::convertirADTO);
    }

    @Override
    @Transactional
    public TipoHabitacionDTO guardarTipoHabitacion(TipoHabitacionDTO tipoHabitacionDTO) {
        TipoHabitacion tipoHabitacion = new TipoHabitacion();
        tipoHabitacion.setNombre(tipoHabitacionDTO.getNombre());
        tipoHabitacion.setDescripcion(tipoHabitacionDTO.getDescripcion());
        tipoHabitacion.setCapacidad(tipoHabitacionDTO.getCapacidad());
        tipoHabitacion.setPrecioBase(tipoHabitacionDTO.getPrecioBase());
        tipoHabitacion.setActivo(tipoHabitacionDTO.isActivo());
        tipoHabitacion.setCreatedAt(LocalDateTime.now());
        if (tipoHabitacionDTO.getHotelId() != null){
            hotelRepository.findById(tipoHabitacionDTO.getHotelId())
                    .ifPresent(tipoHabitacion::setHotel);
        }

        TipoHabitacion tipoHabitacionGuardada = tipoHabitacionRepository.save(tipoHabitacion);
        return convertirADTO(tipoHabitacionGuardada);
    }

    @Override
    @Transactional
    public Optional<TipoHabitacionDTO> actualiarTipoHabitacion(TipoHabitacionDTO tipoHabitacionDTO, long id) {
        return tipoHabitacionRepository.findById(id).map(tipoHabitacion -> {
            tipoHabitacion.setNombre(tipoHabitacionDTO.getNombre());
            tipoHabitacion.setDescripcion(tipoHabitacionDTO.getDescripcion());
            tipoHabitacion.setCapacidad(tipoHabitacionDTO.getCapacidad());
            tipoHabitacion.setPrecioBase(tipoHabitacionDTO.getPrecioBase());
            tipoHabitacion.setActivo(tipoHabitacionDTO.isActivo());
            tipoHabitacion.setCreatedAt(tipoHabitacionDTO.getCreatedAt());
            if (tipoHabitacionDTO.getHotelId() != null){
                hotelRepository.findById(tipoHabitacionDTO.getHotelId())
                        .ifPresent(tipoHabitacion::setHotel);
            }
            TipoHabitacion tipoHabitacionActualizado = tipoHabitacionRepository.save(tipoHabitacion);
            return convertirADTO(tipoHabitacionActualizado);
        });
    }

    @Override
    @Transactional
    public void eliminarTipoHabitacionPorId(long id) {
        tipoHabitacionRepository.deleteById(id);
    }

    @Override
    public TipoHabitacionDTO convertirADTO(TipoHabitacion tipoHabitacion) {
        TipoHabitacionDTO dto = new TipoHabitacionDTO();
        dto.setId(tipoHabitacion.getIdTipoHabitacion());
        dto.setNombre(tipoHabitacion.getNombre());
        dto.setDescripcion(tipoHabitacion.getDescripcion());
        dto.setCapacidad(tipoHabitacion.getCapacidad());
        dto.setPrecioBase(tipoHabitacion.getPrecioBase());
        dto.setActivo(tipoHabitacion.isActivo());
        dto.setCreatedAt(tipoHabitacion.getCreatedAt());
        if (tipoHabitacion.getHotel() != null){
            dto.setHotelId(tipoHabitacion.getHotel().getIdHotel());
            dto.setNombreHotel(tipoHabitacion.getHotel().getNombre());
        }
        return dto;
    }
}
