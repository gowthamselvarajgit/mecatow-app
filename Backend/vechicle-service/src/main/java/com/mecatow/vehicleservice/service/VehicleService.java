package com.mecatow.vehicleservice.service;

import com.mecatow.vehicleservice.dto.AddVehicleRequestDto;
import com.mecatow.vehicleservice.dto.AddVehicleResponseDto;
import com.mecatow.vehicleservice.dto.UpdateVehicleRequestDto;
import com.mecatow.vehicleservice.dto.VehicleResponseDto;
import com.mecatow.vehicleservice.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    AddVehicleResponseDto addVehicle(AddVehicleRequestDto addVehicleRequestDto, Long ownerId);

    VehicleResponseDto getVehicleById(Long vehicleId);

    List<VehicleResponseDto> getVehiclesByOwnerId(Long ownerId);

    VehicleResponseDto updateVehicle(Long vehicleId, UpdateVehicleRequestDto updateVehicleRequestDto);
}
