package com.mecatow.vehicleservice.service.impl;

import com.mecatow.vehicleservice.dto.*;
import com.mecatow.vehicleservice.entity.Vehicle;
import com.mecatow.vehicleservice.exception.InvalidManufacturingYearException;
import com.mecatow.vehicleservice.exception.VehicleAlreadyExistsException;
import com.mecatow.vehicleservice.exception.VehicleNotFoundException;
import com.mecatow.vehicleservice.mapper.VehicleMapper;
import com.mecatow.vehicleservice.repository.VehicleRepository;
import com.mecatow.vehicleservice.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public AddVehicleResponseDto addVehicle(AddVehicleRequestDto addVehicleRequestDto, Long ownerId) {

        if (vehicleRepository.existsByVehicleNumber(addVehicleRequestDto.getVehicleNumber())){
            throw new VehicleAlreadyExistsException("This Vehicle Number is already registered");
        }

        if (addVehicleRequestDto.getManufacturingYear() != null && addVehicleRequestDto.getManufacturingYear() > Year.now().getValue()){
            throw new InvalidManufacturingYearException("Manufacturing Year cannot be in future");
        }

        Vehicle vehicle = VehicleMapper.toEntity(addVehicleRequestDto, ownerId);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return VehicleMapper.toDto(savedVehicle);
    }

    @Override
    public VehicleResponseDto getVehicleById(Long vehicleId) {

        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() ->
                        new VehicleNotFoundException(
                                "Vehicle does not exist with Vehicle Id: " + vehicleId
                        ));

        return VehicleMapper.toVehicleResponseDto(vehicle);
    }

    @Override
    public List<VehicleResponseDto> getVehiclesByOwnerId(Long ownerId) {

        return vehicleRepository
                .findByOwnerId(ownerId)
                .stream()
                .map(vehicle -> VehicleMapper.toVehicleResponseDto(vehicle))
                .toList();
    }

    @Override
    public VehicleResponseDto updateVehicle(Long vehicleId, UpdateVehicleRequestDto updateVehicleRequestDto) {
        Vehicle vehicle = vehicleRepository
                .findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException( "Vehicle does not exist with Vehicle Id: " + vehicleId));

        if (updateVehicleRequestDto.getManufacturingYear() != null && updateVehicleRequestDto.getManufacturingYear() > Year.now().getValue()){
            throw new InvalidManufacturingYearException("Manufacturing Year cannot be in future");
        }

        vehicle.setVehicleType(updateVehicleRequestDto.getVehicleType());
        vehicle.setFuelType(updateVehicleRequestDto.getFuelType());
        vehicle.setBrand(updateVehicleRequestDto.getBrand());
        vehicle.setModel(updateVehicleRequestDto.getModel());
        vehicle.setManufacturingYear(updateVehicleRequestDto.getManufacturingYear() != null ? Year.of(updateVehicleRequestDto.getManufacturingYear()) : null);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        return VehicleMapper.toVehicleResponseDto(savedVehicle);
    }

    @Override
    public DeleteResponseDto deleteVehicleById(Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new VehicleNotFoundException( "Vehicle does not exist with Vehicle Id: " + vehicleId));

        DeleteResponseDto deleteResponseDto = DeleteResponseDto
                .builder()
                .vehicleId(vehicle.getVehicleId())
                .vehicleNumber(vehicle.getVehicleNumber())
                .message("Vehicle deleted successfully")
                .build();

        vehicleRepository.delete(vehicle);

        return deleteResponseDto;
    }
}
