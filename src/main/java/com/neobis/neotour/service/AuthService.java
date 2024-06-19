package com.neobis.neotour.service;

import com.neobis.neotour.dto.LoginRequestDto;
import com.neobis.neotour.dto.LoginResponseDto;
import com.neobis.neotour.dto.RegisterRequestDto;
import com.neobis.neotour.dto.RegisterResponseDto;

public interface AuthService {
    RegisterResponseDto register(RegisterRequestDto registerRequestDto);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
