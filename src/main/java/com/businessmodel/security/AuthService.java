package com.businessmodel.security;

import com.businessmodel.dto.LoginRequestDto;
import com.businessmodel.dto.LoginResponseDto;
import com.businessmodel.dto.SignupResponseDto;
import com.businessmodel.entity.User;
import com.businessmodel.repository.UserRepository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepo userRepo;


    public LoginResponseDto login(LoginRequestDto loginRequestDto){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                )
        );

        User user = userRepo.findByUsername(loginRequestDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token  = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId());
    }

    private final PasswordEncoder passwordEncoder;

    public SignupResponseDto signup(LoginRequestDto signupRequestDto){
        User user = userRepo.findByUsername(signupRequestDto.getUsername()).orElse(null);

        if(user != null){
            throw new IllegalArgumentException("User Already exists");
        }

        user = userRepo.save(User.builder()
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build());

        return new SignupResponseDto(user.getId(), user.getUsername());
    }
}