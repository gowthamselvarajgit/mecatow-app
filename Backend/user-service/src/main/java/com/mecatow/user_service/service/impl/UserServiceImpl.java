package com.mecatow.user_service.service.impl;

import com.mecatow.user_service.dto.*;
import com.mecatow.user_service.entity.User;
import com.mecatow.user_service.enums.Role;
import com.mecatow.user_service.exception.InvalidCredentialsException;
import com.mecatow.user_service.exception.UserAlreadyExistsException;
import com.mecatow.user_service.mapper.UserMapper;
import com.mecatow.user_service.repository.UserRepository;
import com.mecatow.user_service.security.JwtService;
import com.mecatow.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto) {

        if (userRepository.existsByEmail(registerUserRequestDto.getEmail())){
            throw new UserAlreadyExistsException("Email already Exists");
        }

        if (userRepository.existsByMobileNumber(registerUserRequestDto.getMobileNumber())){
            throw new UserAlreadyExistsException("Mobile Number already Exists");
        }

        User user = UserMapper.toUser(registerUserRequestDto);
        user.setRole(Role.CUSTOMER);
        user.setPassword(
                passwordEncoder.encode(
                        registerUserRequestDto.getPassword()
                )
        );

        User savedUser = userRepository.save(user);

        return UserMapper.toRegisterUserResponseDto(savedUser);
    }

    @Override
    public LoginResponseDto loginUser(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new InvalidCredentialsException("Email or Password is Incorrect"));

        if (!passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Email or Password is Incorrect");
        }

        String token = jwtService.generateToken(user);
        return LoginResponseDto.builder()
                .token(token)
                .build();
    }

    @Override
    public ProfileResponseDto getProfile() {
        String email = ((UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        return UserMapper.toProfileResponseDto(user);
    }
}
