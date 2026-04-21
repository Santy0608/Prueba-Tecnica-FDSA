package com.altairis_booking_system.Backend.controller;


import com.altairis_booking_system.Backend.domain.Disponibilidad;
import com.altairis_booking_system.Backend.dto.DisponibilidadDTO;
import com.altairis_booking_system.Backend.service.DisponibilidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/disponibilidades")
public class DisponibilidadController {

    @Autowired
    private DisponibilidadService disponibilidadService;

    @GetMapping
    public List<DisponibilidadDTO> listadoDisponibilidades(){
        return disponibilidadService.listadoDisponibilidades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisponibilidadDTO> buscarDisponibilidadPorId(@PathVariable long id){
        Optional<DisponibilidadDTO> disponibilidadOptional = disponibilidadService.buscarDisponibilidadPorId(id);
        if (disponibilidadOptional.isPresent()){
            return ResponseEntity.ok(disponibilidadOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> guardarDisponibilidad(@RequestBody DisponibilidadDTO disponibilidadDTO, BindingResult result){
        ResponseEntity<Map<String, String>> errors = validation((result));
        if (errors != null){
            return errors;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(disponibilidadService.guardarDisponibilidad(disponibilidadDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDisponibilidad(@RequestBody DisponibilidadDTO disponibilidadDTO, BindingResult result, @PathVariable long id){
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach( error -> {
                errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(null);
        }
        return disponibilidadService.actualizarDisponibilidad(disponibilidadDTO, id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
