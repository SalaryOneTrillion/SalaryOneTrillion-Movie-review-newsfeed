package com.sparta.salaryonetrillionmoviereviewnewsfeed.controller;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.*;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.security.UserDetailsImpl;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@Valid @RequestBody SignupRequestDto requestDto) {
        userService.signupUser(requestDto);
        return ResponseEntity.status(201).body("회원가입 되었습니다.");
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logoutUser(@AuthenticationPrincipal UserDetailsImpl userDetails,
                                             HttpServletResponse response) {

        userService.logoutUser(userDetails.getUser(), response);
        return ResponseEntity.status(200).body("로그아웃 완료");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateProfile(@PathVariable Long userId,
                                                @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                @Valid @RequestBody UserProfileEditRequestDto requestDto) {

        userService.editProfile(userId, userDetails.getUser(), requestDto);
        return ResponseEntity.status(200).body("프로필이 수정되었습니다.");
    }

    @PatchMapping("/{userId}/updateemail")
    public ResponseEntity<String> updateEmail(@PathVariable Long userId, @AuthenticationPrincipal
    UserDetailsImpl userDetails, @Valid @RequestBody UserEmailRequestDto requestDto) {

        userService.updateEmail(userId, userDetails.getUser(), requestDto);

        return ResponseEntity.status(200).body("이메일이 수정되었습니다.");
    }

    @PatchMapping("/{userId}/updatenickname")
    public ResponseEntity<String> updateNickname(@PathVariable Long userId,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                 @Valid @RequestBody UserNicknameDto requestDto) {

        userService.updateNickname(userId, userDetails.getUser(), requestDto);

        return ResponseEntity.status(200).body("닉네임이 수정되었습니다.");
    }

    @PatchMapping("/{userId}/updatepassword")
    public ResponseEntity<String> updatePassword(@PathVariable Long userId,
                                                 @AuthenticationPrincipal UserDetailsImpl userDetails,
                                                 @Valid @RequestBody UserPasswordDto requestDto) {

        userService.updatePassword(userId, userDetails.getUser(), requestDto);

        return ResponseEntity.status(200).body("비밀번호가 수정되었습니다.");
    }
}
