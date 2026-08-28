package com.mecatow.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDto {

    @NotBlank(message = "Email is Required")
    @Email(message = "Enter Valid Email Id")
    private String email;

    @NotBlank(message = "Password is Required")
    private String password;

}
