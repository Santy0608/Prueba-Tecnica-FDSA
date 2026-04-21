package com.altairis_booking_system.Backend.serviceImpl;

import com.altairis_booking_system.Backend.domain.Disponibilidad;
import com.altairis_booking_system.Backend.dto.DisponibilidadDTO;
import com.altairis_booking_system.Backend.repository.DisponibilidadRepository;
import com.altairis_booking_system.Backend.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DisponibilidadServiceImpl implements DisponibilidadService {

    @Autowired
    private DisponibilidadRepository disponibilidadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DisponibilidadDTO> listadoDisponibilidades() {
        List<Disponibilidad> disponibilidades = disponibilidadRepository.findAll();
    }

    @Override
    public Optional<DisponibilidadDTO> buscarDisponibilidadPorId(long id) {
        return Optional.empty();
    }

    @Override
    public DisponibilidadDTO guardarDisponibilidad(DisponibilidadDTO disponibilidadDTO) {
        return null;
    }

    @Override
    public Optional<DisponibilidadDTO> actualizarDisponibilidad(DisponibilidadDTO disponibilidadDTO, long id) {
        return Optional.empty();
    }

    @Override
    public DisponibilidadDTO convertirADTO(Disponibilidad disponibilidad) {
        return null;
    }
}
