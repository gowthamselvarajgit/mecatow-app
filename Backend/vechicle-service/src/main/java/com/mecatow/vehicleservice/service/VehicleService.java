package com.mecatow.vehicleservice.service;

import com.mecatow.vehicleservice.dto.*;
import com.mecatow.vehicleservice.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    AddVehicleResponseDto addVehicle(AddVehicleRequestDto addVehicleRequestDto, Long ownerId);

    VehicleResponseDto getVehicleById(Long vehicleId);

    List<VehicleResponseDto> getVehiclesByOwnerId(Long ownerId);

    VehicleResponseDto updateVehicle(Long vehicleId, UpdateVehicleRequestDto updateVehicleRequestDto);

    DeleteResponseDto deleteVehicleById(Long vehicleId);
}
