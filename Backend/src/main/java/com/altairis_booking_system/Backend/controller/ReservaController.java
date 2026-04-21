package com.altairis_booking_system.Backend.controller;

import com.altairis_booking_system.Backend.domain.EstadoReserva;
import com.altairis_booking_system.Backend.domain.Reserva;
import com.altairis_booking_system.Backend.dto.ReservaDTO;
import com.altairis_booking_system.Backend.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<ReservaDTO> listadoReservas(){
        return reservaService.listadoReservas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarReservaPorId(@PathVariable long id){
        Optional<ReservaDTO> reservaOptional = reservaService.buscarReservaPorId(id);
        if (reservaOptional.isPresent()){
            return ResponseEntity.ok(reservaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<ReservaDTO>> filtrar(
            @RequestParam(required = false) EstadoReserva estado,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin) {
        return ResponseEntity.ok(reservaService.filtrar(estado, nombre, fechaInicio, fechaFin));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReservaDTO>> findByEstado(@PathVariable EstadoReserva estado) {
        return ResponseEntity.ok(reservaService.buscarPorEstado(estado));
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<ReservaDTO>> findByFechas(
            @RequestParam LocalDate inicio,
            @RequestParam LocalDate fin) {
        return ResponseEntity.ok(reservaService.buscarPorFechas(inicio, fin));
    }

    @PostMapping
    public ResponseEntity<?> guardarReserva(@RequestBody ReservaDTO reservaDTO, BindingResult result){
        ResponseEntity<Map<String, String>> errors = validation((result));
        if (errors != null){
            return errors;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crearReserva(reservaDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReserva(@RequestBody ReservaDTO reservaDTO, BindingResult result, @PathVariable long id){
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach( error -> {
                errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(reservaService.actualizarReserva(reservaDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReserva(@PathVariable long id){
        Optional<ReservaDTO> reservaOptional = reservaService.buscarReservaPorId(id);
        if (reservaOptional.isPresent()){
            reservaService.eliminarReservaPorId(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Void> actualizarEstado(
            @PathVariable Long id,
            @RequestParam EstadoReserva estado) {
        reservaService.actualizarEstado(id, estado);
        return ResponseEntity.noContent().build();
    }

    private static ResponseEntity<Map<String, String>> validation(BindingResult result) {
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(errors);
        }
        return null;
    }

}
