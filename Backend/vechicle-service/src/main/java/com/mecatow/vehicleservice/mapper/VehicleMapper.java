package com.mecatow.vehicleservice.mapper;

import com.mecatow.vehicleservice.dto.AddVehicleRequestDto;
import com.mecatow.vehicleservice.dto.AddVehicleResponseDto;
import com.mecatow.vehicleservice.dto.VehicleResponseDto;
import com.mecatow.vehicleservice.entity.Vehicle;

import java.time.Year;

public class VehicleMapper {

    public static Vehicle toEntity(AddVehicleRequestDto addVehicleRequestDto, Long ownerId){
        return Vehicle.builder()
                .vehicleNumber(addVehicleRequestDto.getVehicleNumber())
                .vehicleType(addVehicleRequestDto.getVehicleType())
                .fuelType(addVehicleRequestDto.getFuelType())
                .brand(addVehicleRequestDto.getBrand())
                .model(addVehicleRequestDto.getModel())
                .manufacturingYear(
                        addVehicleRequestDto.getManufacturingYear() != null ?
                        Year.of(addVehicleRequestDto.getManufacturingYear())
                                :null
                )
                .ownerId(ownerId)
                .build();
    }

    public static AddVehicleResponseDto toDto(Vehicle vehicle){
        return AddVehicleResponseDto.builder()
                .vehicleId(vehicle.getVehicleId())
                .vehicleNumber(vehicle.getVehicleNumber())
                .message("Vehicle Added Successfully")
                .build();
    }

    public static VehicleResponseDto toVehicleResponseDto(Vehicle vehicle){
        return VehicleResponseDto.builder()
                .vehicleId(vehicle.getVehicleId())
                .vehicleNumber(vehicle.getVehicleNumber())
                .vehicleType(vehicle.getVehicleType())
                .fuelType(vehicle.getFuelType())
                .brand(vehicle.getBrand())
                .model(vehicle.getModel())
                .manufacturingYear(vehicle.getManufacturingYear() != null
                        ? vehicle.getManufacturingYear().getValue()
                        :null
                )
                .build();
    }


}
