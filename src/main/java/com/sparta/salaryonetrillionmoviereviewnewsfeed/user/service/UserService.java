package com.sparta.salaryonetrillionmoviereviewnewsfeed.user.service;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.dto.SignupRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.dto.UserEmailRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.dto.UserNicknameDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.dto.UserPasswordDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.dto.UserProfileUpdateRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.dto.UserResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.entity.User;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.entity.UserRoleEnum;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.jwt.JwtUtil;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

        if (userRepository.existsByUsernameOrNicknameOrEmail(
                requestDto.getUsername(),
                requestDto.getNickname(),
                requestDto.getEmail()
        )) {
            throw new IllegalArgumentException("사용자 아이디, 이메일 또는 닉네임이 이미 사용 중 입니다.");
        }

        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();
        String email = requestDto.getEmail();
        String introduction = requestDto.getIntroduction();
        UserRoleEnum role = UserRoleEnum.USER;
        if(requestDto.getRole().equals("이것은어드민키입니다")) {
            role = UserRoleEnum.ADMIN;
        }

        User user = new User(username, password, nickname, email, introduction, role);

        userRepository.save(user);

        return new UserResponseDto(user);
    }

    @Transactional
    public void updateProfile(Long userId, User user, UserProfileUpdateRequestDto requestDto) {

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
