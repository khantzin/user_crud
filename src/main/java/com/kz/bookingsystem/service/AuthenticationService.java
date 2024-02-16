package com.kz.bookingsystem.service;

import com.kz.bookingsystem.config.JwtService;
import com.kz.bookingsystem.controller.authentication.AuthenticationRequest;
import com.kz.bookingsystem.controller.authentication.AuthenticationResponse;
import com.kz.bookingsystem.dto.UserDTO;
import com.kz.bookingsystem.entity.User;
import com.kz.bookingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface AuthenticationService {

    AuthenticationResponse register(UserDTO request);

    Map<String, String> authenticate(AuthenticationRequest request);

    Map<String, String> sendEmail(String email);

    Map<String, String> validateOtp(String email, String otpId, String otpCode);

    Map<String, String> updatePasord(AuthenticationRequest request);
}
