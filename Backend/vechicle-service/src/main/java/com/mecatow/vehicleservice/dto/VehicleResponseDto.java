package com.mecatow.vehicleservice.dto;

import com.mecatow.vehicleservice.enums.FuelType;
import com.mecatow.vehicleservice.enums.VehicleType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleResponseDto {

    private Long vehicleId;
    private String vehicleNumber;
    private VehicleType vehicleType;
    private FuelType fuelType;
    private String brand;
    private String model;
    private Integer manufacturingYear;

}
