package com.altairis_booking_system.Backend.controller;

import com.altairis_booking_system.Backend.dto.HotelDTO;
import com.altairis_booking_system.Backend.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/hoteles")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    public List<HotelDTO> listadoHoteles(){
        return hotelService.listadoHoteles();
    }

    @GetMapping("/{idHotel}")
    public ResponseEntity<HotelDTO> buscarHotelPorId(@PathVariable long idHotel){
        Optional<HotelDTO> hotelOptinoal = hotelService.buscarHotelPorId(idHotel);
        if (hotelOptinoal.isPresent()){
            return ResponseEntity.ok(hotelOptinoal.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> guardarHotel(@RequestBody HotelDTO hotelDTO, BindingResult result){
        ResponseEntity<Map<String, String>> errors = validation(result);
        if (errors != null){
            return errors;
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelService.guardarHotel(hotelDTO));
    }

    @PutMapping("/{idHotel}")
    public ResponseEntity<?> actualizarHotel(@RequestBody HotelDTO hotelDTO, BindingResult result, @PathVariable long idHotel){
        if (result.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach( error -> {
                errors.put(error.getField(), "El campo " + error.getField() + " " + error.getDefaultMessage());
            });
            return ResponseEntity.badRequest().body(null);
        }
        return hotelService.actualizarHotel(hotelDTO, idHotel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{idHotel}")
    public ResponseEntity<Void> eliminarHotel(@PathVariable Long idHotel){
        Optional<HotelDTO> hotelOptional = hotelService.buscarHotelPorId(idHotel);
        if (hotelOptional.isPresent()){
            hotelService.eliminarHotelPorId(idHotel);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<HotelDTO>> buscar(@RequestParam String nombre) {
        return ResponseEntity.ok(hotelService.buscarPorNombre(nombre));
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
