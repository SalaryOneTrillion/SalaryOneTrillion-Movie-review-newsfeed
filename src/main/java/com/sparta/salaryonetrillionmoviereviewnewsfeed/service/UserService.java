package com.sparta.salaryonetrillionmoviereviewnewsfeed.service;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.SignupRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.UserResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.User;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.UserRoleEnum;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.jwt.JwtUtil;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signupUser(SignupRequestDto requestDto) {

        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();
        String email = requestDto.getEmail();
        String introduction = requestDto.getIntroduction();
        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(username, password, nickname, email, introduction, role);

        userRepository.save(user);

        return new UserResponseDto(user);
    }
}
