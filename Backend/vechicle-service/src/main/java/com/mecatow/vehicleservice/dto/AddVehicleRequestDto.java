package com.mecatow.vehicleservice.dto;

import com.mecatow.vehicleservice.enums.FuelType;
import com.mecatow.vehicleservice.enums.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddVehicleRequestDto {
    @NotBlank(message = "Vehicle Number is required")
    @Size(max = 20, message = "Vehicle Number cannot exceed 20 characters")
    @Pattern(
            regexp = "^[A-Z]{2}[0-9]{2}[A-Z]{1,3}[0-9]{1,4}$",
            message = "Invalid vehicle number format"
    )
    private String vehicleNumber;

    @NotNull(message = "Vehicle Type is required")
    private VehicleType vehicleType;

    @NotNull(message = "Fuel Type is required")
    private FuelType fuelType;

    @NotBlank(message = "Brand is required")
    @Size(max = 50, message = "Brand cannot exceed 50 characters")
    private String brand;

    @NotBlank(message = "Model is required")
    @Size(max = 50, message = "Model cannot exceed 50 characters")
    private String model;

    private Integer manufacturingYear;
}
