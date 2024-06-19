package com.neobis.neotour.service;

import com.neobis.neotour.dto.LoginRequestDto;
import com.neobis.neotour.dto.LoginResponseDto;
import com.neobis.neotour.dto.RegisterRequestDto;
import com.neobis.neotour.dto.RegisterResponseDto;
import com.neobis.neotour.enums.UserRole;
import com.neobis.neotour.exceptions.ResourceExistsException;
import com.neobis.neotour.exceptions.ResourceNotFoundException;
import com.neobis.neotour.model.Image;
import com.neobis.neotour.model.User;
import com.neobis.neotour.repository.ImageRepository;
import com.neobis.neotour.repository.UserRepository;
import com.neobis.neotour.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public RegisterResponseDto register(RegisterRequestDto registerRequestDto) {
        boolean usernameExists = userRepository.existsByUsername(registerRequestDto.getUsername());
        if (usernameExists) {
            throw new ResourceExistsException("User with username " + registerRequestDto.getUsername() + " already exists.");
        }

        Optional<Image> image = imageRepository.findById(registerRequestDto.getImageId());
        if (image.isEmpty()) {
            throw new ResourceNotFoundException("Image with id " + registerRequestDto.getImageId() + " does not exist.");
        }

        User user = new User();
        user.setUsername(registerRequestDto.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        user.setRole(UserRole.USER_ROLE);
        user.setImage(image.get());
        userRepository.save(user);
        String accessToken = jwtService.generateToken(user, user.getId());
        return RegisterResponseDto.builder().accessToken(accessToken).build();
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );
        Optional<User> user = userRepository.findByUsername(loginRequestDto.getUsername());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User with username " + loginRequestDto.getUsername() + " does not exist.");
        }

        User userModel = user.get();
        String accessToken = jwtService.generateToken(userModel, userModel.getId());
        return LoginResponseDto.builder().accessToken(accessToken).build();
    }
}
