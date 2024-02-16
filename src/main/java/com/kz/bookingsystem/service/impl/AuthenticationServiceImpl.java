package com.kz.bookingsystem.service.impl;

import com.kz.bookingsystem.config.JwtService;
import com.kz.bookingsystem.controller.authentication.AuthenticationRequest;
import com.kz.bookingsystem.controller.authentication.AuthenticationResponse;
import com.kz.bookingsystem.dto.UserDTO;
import com.kz.bookingsystem.entity.User;
import com.kz.bookingsystem.exception.CoreApiException;
import com.kz.bookingsystem.repository.UserRepository;
import com.kz.bookingsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(UserDTO request) {
        var user = User.builder()
                .userid(request.getUserId())
                .name(request.getUserName())
                .email(request.getEmail())
                .countryId(request.getCountryId())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public Map<String, String> authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserId(),
                        request.getPassword()
                )
        );
        try {
            var user = repository.findUserByUserid(request.getUserId())
                    .orElseThrow();
            var jwtToken = jwtService.generateToken(user);

            var map = Map.of("token", jwtToken,
                    "userId", user.getUserid(),
                    "userName", user.getName(),
                    "email", user.getEmail()
            );
            return map;
        }
        catch (CoreApiException ex) {
            throw new CoreApiException(ex.getTitle(), ex.getMessage(), ex.getCode());
        }
    }

    @Override
    public Map<String, String> sendEmail(String email) {
        try{
            // TODO: Generate otp Code
            // TODO: save OTP Code to DB
            var map = Map.of(
                    "success", "Email already send to your inbox.",
                    "otpId", "1"
                );
            return map;
        }
        catch (CoreApiException ex) {
            throw new CoreApiException(ex.getTitle(), ex.getCode(), ex.getMessage());
        }
    }

    @Override
    public Map<String, String> validateOtp(String email, String otpId, String otpCode) {
        try{
            //TODO: getOTPBy Email and OTP id
            //TODO: validete code.

            var map = Map.of(
                "success", "OTP is Valid",
                "code", "success"
            );
            return map;
        }
        catch (CoreApiException ex) {
            throw new CoreApiException(ex.getTitle(), ex.getCode(), ex.getMessage());
        }
    }

    @Override
    public Map<String, String> updatePasord(AuthenticationRequest request) {
        try {
            var user = repository.findById(Integer.parseInt(request.getUserId()));
            if(user.isEmpty()) {
                throw new CoreApiException("Fail to update Password", request.getUserId(), "User not found.");
            }
            user.get().setPassword(passwordEncoder.encode(request.getPassword()));
            repository.save(user.get());
            var map = Map.of(
                "success", "Password Changed Successfully."
            );
            return map;
        }
        catch (CoreApiException ex) {
            throw new CoreApiException(ex.getTitle(), ex.getCode(), ex.getMessage());
        }
    }
}
