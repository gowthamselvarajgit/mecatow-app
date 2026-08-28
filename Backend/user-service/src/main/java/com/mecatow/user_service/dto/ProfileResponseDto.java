package com.mecatow.user_service.dto;

import com.mecatow.user_service.enums.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponseDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String mobileNumber;
    private Role role;
}
