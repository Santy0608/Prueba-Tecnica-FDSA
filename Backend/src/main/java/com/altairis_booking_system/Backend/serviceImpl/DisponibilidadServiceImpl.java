package com.altairis_booking_system.Backend.serviceImpl;

import com.altairis_booking_system.Backend.domain.Disponibilidad;
import com.altairis_booking_system.Backend.dto.DisponibilidadDTO;
import com.altairis_booking_system.Backend.repository.DisponibilidadRepository;
import com.altairis_booking_system.Backend.repository.TipoHabitacionRepository;
import com.altairis_booking_system.Backend.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisponibilidadServiceImpl implements DisponibilidadService {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DisponibilidadDTO> listadoDisponibilidades() {
        List<Disponibilidad> disponibilidades = disponibilidadRepository.findAll();
        List<DisponibilidadDTO> dtos = disponibilidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DisponibilidadDTO> buscarDisponibilidadPorId(long id) {
        return disponibilidadRepository.findById(id).map(this::convertirADTO);
    }

    @Override
    @Transactional
    public DisponibilidadDTO guardarDisponibilidad(DisponibilidadDTO disponibilidadDTO) {
        Disponibilidad disponibilidad = new Disponibilidad();
        disponibilidad.setFecha(disponibilidadDTO.getFecha());
        disponibilidad.setCantidadTotal(disponibilidadDTO.getCantidadTotal());
        disponibilidad.setCantidadDisponible(disponibilidadDTO.getCantidadDisponible());
        disponibilidad.setPrecio(disponibilidadDTO.getPrecio());
        disponibilidad.setCreatedAt(LocalDateTime.now());
        if (disponibilidadDTO.getTipoHabitacionId() != null){
            tipoHabitacionRepository.findById(disponibilidadDTO.getTipoHabitacionId())
                    .ifPresent(disponibilidad::setTipoHabitacion);
        }

        Disponibilidad disponibilidadAgregada = disponibilidadRepository.save(disponibilidad);
        return convertirADTO(disponibilidadAgregada);
    }

    @Override
    @Transactional
    public Optional<DisponibilidadDTO> actualizarDisponibilidad(DisponibilidadDTO disponibilidadDTO, long id) {
        return disponibilidadRepository.findById(id).map(disponibilidad -> {
            disponibilidad.setFecha(disponibilidadDTO.getFecha());
            disponibilidad.setCantidadTotal(disponibilidadDTO.getCantidadTotal());
            disponibilidad.setCantidadDisponible(disponibilidadDTO.getCantidadDisponible());
            disponibilidad.setPrecio(disponibilidadDTO.getPrecio());
            disponibilidad.setCreatedAt(disponibilidadDTO.getCreatedAt());
            if (disponibilidadDTO.getTipoHabitacionId() != null){
                tipoHabitacionRepository.findById(disponibilidadDTO.getTipoHabitacionId())
                        .ifPresent(disponibilidad::setTipoHabitacion);
            }
            Disponibilidad disponibilidadActualizada = disponibilidadRepository.save(disponibilidad);
            return convertirADTO(disponibilidadActualizada);
        });
    }

    @Override
    public DisponibilidadDTO convertirADTO(Disponibilidad disponibilidad) {
        DisponibilidadDTO dto = new DisponibilidadDTO();
        dto.setId(disponibilidad.getId());
        dto.setFecha(disponibilidad.getFecha());
        dto.setCantidadTotal(disponibilidad.getCantidadTotal());
        dto.setPrecio(disponibilidad.getPrecio());
        dto.setCreatedAt(disponibilidad.getCreatedAt());
        if (disponibilidad.getTipoHabitacion() != null){
            dto.setTipoHabitacionId(disponibilidad.getTipoHabitacion().getIdTipoHabitacion());
            dto.setNombreTipoHabitacion(disponibilidad.getTipoHabitacion().getNombre());
        }
        return dto;
    }
}
