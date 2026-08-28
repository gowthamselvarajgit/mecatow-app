package com.mecatow.user_service.service;

import com.mecatow.user_service.dto.*;

public interface UserService {

    RegisterUserResponseDto registerUser(RegisterUserRequestDto registerUserRequestDto);

    LoginResponseDto loginUser(LoginRequestDto loginRequestDto);

    ProfileResponseDto getProfile();
}
