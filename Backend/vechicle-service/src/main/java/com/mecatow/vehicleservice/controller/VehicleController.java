package com.mecatow.vehicleservice.controller;

import com.mecatow.vehicleservice.dto.*;
import com.mecatow.vehicleservice.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<AddVehicleResponseDto> registerVehicle(@RequestBody @Valid AddVehicleRequestDto addVehicleRequestDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        vehicleService.addVehicle(
                        addVehicleRequestDto,
                        1L
                        )
                );
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseDto> getVehicleById(@PathVariable Long vehicleId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        vehicleService
                                .getVehicleById(vehicleId)
                );
    }

    @GetMapping("/my-vehicles")
    public ResponseEntity<List<VehicleResponseDto>> getVehicleByOwnerId(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        vehicleService
                                .getVehiclesByOwnerId(1L)
                );
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<VehicleResponseDto> updateVehicleById(@PathVariable Long vehicleId, @RequestBody @Valid UpdateVehicleRequestDto updateVehicleRequestDto){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(vehicleService
                        .updateVehicle(vehicleId, updateVehicleRequestDto)
                );
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<DeleteResponseDto> deleteVehicleById(@PathVariable Long vehicleId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        vehicleService
                                .deleteVehicleById(vehicleId)
                );
    }


}
