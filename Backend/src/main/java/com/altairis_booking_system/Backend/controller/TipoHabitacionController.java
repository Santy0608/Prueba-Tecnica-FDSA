package com.altairis_booking_system.Backend.controller;

import com.altairis_booking_system.Backend.dto.HotelDTO;
import com.altairis_booking_system.Backend.dto.TipoHabitacionDTO;
import com.altairis_booking_system.Backend.service.TipoHabitacionService;
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
@RequestMapping("/api/tipos-habitaciones")
public class TipoHabitacionController {

    @Autowired
    private TipoHabitacionService tipoHabitacionService;

    @GetMapping
    public List<TipoHabitacionDTO> listadoTiposHabitaciones(){
        return tipoHabitacionService.listadoTiposHabitaciones();
    }

    @GetMapping("/{idTipoHabitacion}")
    public ResponseEntity<TipoHabitacionDTO> buscarTipoHabitacionPorId(@PathVariable long idTipoHabitacion){
        Optional<TipoHabitacionDTO> tipoHabitacionOptional = tipoHabitacionService.buscarTipoHabitacionPorId(idTipoHabitacion);
        if (tipoHabitacionOptional.isPresent()){
            return ResponseEntity.ok(tipoHabitacionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TipoHabitacionDTO>> buscar(@RequestParam String nombre) {
        return ResponseEntity.ok(tipoHabitacionService.buscarTipoHabitacionPorNombre(nombre));
    }

    @PostMapping
    public ResponseEntity<?> guardarTipoHabitacion(@RequestBody TipoHabitacionDTO tipoHabitacionDTO, BindingResult result){
        ResponseEntity<Map<String, String>> errors = validation(result);
        if (errors != null){
            return errors;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoHabitacionService.guardarTipoHabitacion(tipoHabitacionDTO));
    }

    @PutMapping("/{idTipoHabitacion}")
    public ResponseEntity<?> actualizarTipoHabitacion(@RequestBody TipoHabitacionDTO tipoHabitacionDTO, BindingResult result, @PathVariable long idTipoHabitacion){
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach( error -> {
                errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(null);
        }
        return tipoHabitacionService.actualiarTipoHabitacion(tipoHabitacionDTO, idTipoHabitacion)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idTipoHabitacion}")
    public ResponseEntity<Void> eliminarTipoHabitacionPorId(@PathVariable long idTipoHabitacion){
        Optional<TipoHabitacionDTO> tipoHabitacionOptional = tipoHabitacionService.buscarTipoHabitacionPorId(idTipoHabitacion);
        if (tipoHabitacionOptional.isPresent()) {
            tipoHabitacionService.eliminarTipoHabitacionPorId(idTipoHabitacion);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
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
