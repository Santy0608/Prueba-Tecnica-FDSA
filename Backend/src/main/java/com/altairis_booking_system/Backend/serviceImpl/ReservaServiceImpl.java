package com.altairis_booking_system.Backend.serviceImpl;

import com.altairis_booking_system.Backend.domain.EstadoReserva;
import com.altairis_booking_system.Backend.domain.Reserva;
import com.altairis_booking_system.Backend.domain.TipoHabitacion;
import com.altairis_booking_system.Backend.dto.ReservaDTO;
import com.altairis_booking_system.Backend.repository.ReservaRepository;
import com.altairis_booking_system.Backend.repository.TipoHabitacionRepository;
import com.altairis_booking_system.Backend.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired private TipoHabitacionRepository tipoHabitacionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> listadoReservas() {
        List<Reserva> reservas = reservaRepository.findAll();
        List<ReservaDTO> dtos = reservas.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
        return dtos;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReservaDTO> buscarReservaPorId(long id) {
        return reservaRepository.findById(id).map(this::convertirADTO);
    }

    @Override
    @Transactional
    public ReservaDTO crearReserva(ReservaDTO reservaDTO) {
        TipoHabitacion tipo = tipoHabitacionRepository.findById(reservaDTO.getTipoHabitacionId())
                .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado"));

        Reserva reserva = toEntity(reservaDTO, tipo);
        return convertirADTO(reservaRepository.save(reserva));
    }

    @Override
    @Transactional
    public ReservaDTO actualizarReserva(ReservaDTO reservaDTO, long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        TipoHabitacion tipo = tipoHabitacionRepository.findById(reservaDTO.getTipoHabitacionId())
                .orElseThrow(() -> new RuntimeException("Tipo de habitación no encontrado"));

        reserva.setTipoHabitacion(tipo);
        reserva.setNombreCliente(reservaDTO.getNombreCliente());
        reserva.setEmailCliente(reservaDTO.getEmailCliente());
        reserva.setTelefonoCliente(reservaDTO.getTelefonoCliente());
        reserva.setFechaCheckin(reservaDTO.getFechaCheckin());
        reserva.setFechaCheckout(reservaDTO.getFechaCheckout());
        reserva.setCantidadHabitaciones(reservaDTO.getCantidadHabitaciones());
        reserva.setPrecioTotal(reservaDTO.getPrecioTotal());
        reserva.setEstado(reservaDTO.getEstado());
        reserva.setNotas(reservaDTO.getNotas());

        return convertirADTO(reservaRepository.save(reserva));
    }

    @Override
    public void actualizarEstado(Long id, EstadoReserva estado) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        reserva.setEstado(estado);
        reservaRepository.save(reserva);
    }

    @Override
    @Transactional
    public void eliminarReservaPorId(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> buscarPorEstado(EstadoReserva estado) {
        return reservaRepository.findByEstado(estado)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaDTO> buscarPorFechas(LocalDate inicio, LocalDate fin) {
        return reservaRepository.findByFechaCheckinBetween(inicio, fin)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReservaDTO convertirADTO(Reserva reserva) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(reserva.getId());
        if (reserva.getTipoHabitacion() != null) {
            dto.setTipoHabitacionId(reserva.getTipoHabitacion().getIdTipoHabitacion());
            dto.setNombreTipoHabitacion(reserva.getTipoHabitacion().getNombre());
        }
        dto.setNombreCliente(reserva.getNombreCliente());
        dto.setEmailCliente(reserva.getEmailCliente());
        dto.setTelefonoCliente(reserva.getTelefonoCliente());
        dto.setFechaCheckin(reserva.getFechaCheckin());
        dto.setFechaCheckout(reserva.getFechaCheckout());
        dto.setCantidadHabitaciones(reserva.getCantidadHabitaciones());
        dto.setPrecioTotal(reserva.getPrecioTotal());
        dto.setEstado(reserva.getEstado());
        dto.setNotas(reserva.getNotas());
        dto.setCreatedAt(reserva.getCreatedAt());
        return dto;
    }

    private Reserva toEntity(ReservaDTO dto, TipoHabitacion tipo) {
        Reserva reserva = new Reserva();
        reserva.setTipoHabitacion(tipo);
        reserva.setNombreCliente(dto.getNombreCliente());
        reserva.setEmailCliente(dto.getEmailCliente());
        reserva.setTelefonoCliente(dto.getTelefonoCliente());
        reserva.setFechaCheckin(dto.getFechaCheckin());
        reserva.setFechaCheckout(dto.getFechaCheckout());
        reserva.setCantidadHabitaciones(dto.getCantidadHabitaciones());
        reserva.setPrecioTotal(dto.getPrecioTotal());
        reserva.setEstado(dto.getEstado() != null ? dto.getEstado() : EstadoReserva.PENDIENTE);
        reserva.setNotas(dto.getNotas());
        return reserva;
    }
}
