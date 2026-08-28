package com.mecatow.user_service.mapper;

import com.mecatow.user_service.dto.LoginResponseDto;
import com.mecatow.user_service.dto.ProfileResponseDto;
import com.mecatow.user_service.dto.RegisterUserRequestDto;
import com.mecatow.user_service.dto.RegisterUserResponseDto;
import com.mecatow.user_service.entity.User;

public class UserMapper {

    public static User toUser(RegisterUserRequestDto registerUserRequestDto){
        return User.builder()
                .firstName(registerUserRequestDto.getFirstName())
                .lastName(registerUserRequestDto.getLastName())
                .email(registerUserRequestDto.getEmail())
                .mobileNumber(registerUserRequestDto.getMobileNumber())
                .password(registerUserRequestDto.getPassword())
                .build();
    }

    public static RegisterUserResponseDto toRegisterUserResponseDto(User user){
        return RegisterUserResponseDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .build();
    }

    public static ProfileResponseDto toProfileResponseDto(User user){
        return ProfileResponseDto.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber())
                .role(user.getRole())
                .build();
    }
}
