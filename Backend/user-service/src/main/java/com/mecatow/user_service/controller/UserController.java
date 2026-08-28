package com.mecatow.user_service.controller;

import com.mecatow.user_service.dto.*;
import com.mecatow.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public RegisterUserResponseDto registerUser(@RequestBody @Valid RegisterUserRequestDto requestDto){
        return userService.registerUser(requestDto);
    }

    @PostMapping("/login")
    public LoginResponseDto loginUser(@RequestBody @Valid LoginRequestDto loginRequestDto){
        return userService.loginUser(loginRequestDto);
    }

    @GetMapping("profile")
    public ProfileResponseDto profile(){
        return userService.getProfile();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String admin(){
        return "Admin Endpoint";
    }

    @PreAuthorize("hasRole('CUSTOMER')")
    @GetMapping("/customer")
    public String customer(){
        return "Customer Endpoint";
    }

    @PreAuthorize("hasRole('MECHANIC')")
    @GetMapping("/mechanic")
    public String mechanic(){
        return "Mechanic Endpoint";
    }
}
