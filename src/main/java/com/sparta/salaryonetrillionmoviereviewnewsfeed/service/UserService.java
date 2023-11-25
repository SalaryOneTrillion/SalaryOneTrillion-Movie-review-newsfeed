package com.sparta.salaryonetrillionmoviereviewnewsfeed.service;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.SignupRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.UserEmailRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.UserNicknameDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.UserPasswordDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.UserProfileEditRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.UserResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.User;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.entity.UserRoleEnum;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.jwt.JwtUtil;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public void editProfile(Long userId, User user, UserProfileEditRequestDto requestDto) {

        user = checkUser(userId, user);
        user.setIntroduction(requestDto.getIntroduction());
    }

    @Transactional
    public void updateEmail(Long userId, User user, UserEmailRequestDto requestDto) {

        user = checkUser(userId, user);
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new IllegalArgumentException("중복된 이메일입니다.");
        }
        user.setEmail(requestDto.getEmail());
    }

    @Transactional
    public void updateNickname(Long userId, User user, UserNicknameDto requestDto) {

        user = checkUser(userId, user);
        if (userRepository.existsByNickname(requestDto.getNickname())) {
            throw new IllegalArgumentException("중복된 닉네임입니다");
        }
        user.setNickname(requestDto.getNickname());
    }

    @Transactional
    public void updatePassword(Long userId, User user, UserPasswordDto requestDto) {

        if (!passwordEncoder.matches(requestDto.getOldPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        user = checkUser(userId, user);
        user.setPassword(passwordEncoder.encode(requestDto.getNewPassword()));
    }

    private User checkUser(Long userId, User user) {

        if (!user.getId().equals(userId)) {
            throw new IllegalArgumentException("수정할 권한이 없습니다.");
        }
        user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("해당 유저는 존재하지 않습니다.")
        );

        return user;
    }


    public void logoutUser(User user, HttpServletResponse response) {
        String username = user.getUsername();
        UserRoleEnum userRole = user.getRole();

        String token = jwtUtil.createLogoutToken(username, userRole);

        jwtUtil.addJwtToCookie(token, response);
    }
}
