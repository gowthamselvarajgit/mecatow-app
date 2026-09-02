package com.mecatow.vehicleservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteResponseDto {
    private Long vehicleId;
    private String vehicleNumber;
    private String message;
}
