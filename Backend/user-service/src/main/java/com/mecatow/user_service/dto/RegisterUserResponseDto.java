package com.mecatow.user_service.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserResponseDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;

}
