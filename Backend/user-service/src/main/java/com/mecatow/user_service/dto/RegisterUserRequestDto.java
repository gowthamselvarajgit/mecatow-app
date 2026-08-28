package com.mecatow.user_service.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterUserRequestDto {

    @NotBlank(message = "First Name is Required")
    private String firstName;

    @NotBlank(message = "Last Name is Required")
    private String lastName;

    @Email(message = "Enter Valid Email Id")
    @NotBlank(message = "Email is Required")
    private String email;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Mobile Number must start with 6-9 and contain exactly 10 digits")
    @NotBlank(message = "Mobile Number is Required")
    private String mobileNumber;

    @NotBlank(message = "Password is Required")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=!]).*$", message = "Password must contain at least one uppercase letter, one lowercase letter, one digit and one special character")
    private String password;
}
