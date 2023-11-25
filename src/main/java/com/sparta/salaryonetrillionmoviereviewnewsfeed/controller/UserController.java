package com.sparta.salaryonetrillionmoviereviewnewsfeed.controller;

import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.SignupRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.UserEmailRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.UserNicknameDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.UserPasswordDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.UserProfileEditRequestDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.dto.UserResponseDto;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.security.UserDetailsImpl;
import com.sparta.salaryonetrillionmoviereviewnewsfeed.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signupUser(@RequestBody SignupRequestDto requestDto) {

        return ResponseEntity.status(201).body(userService.signupUser(requestDto));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> editProfile(@PathVariable Long userId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody UserProfileEditRequestDto requestDto) {

        userService.editProfile(userId, userDetails.getUser(), requestDto);
        return ResponseEntity.status(200).body("수정 완료");
    }

    @PatchMapping("/{userId}/updateemail")
    public ResponseEntity<?> updateEmail(@PathVariable Long userId, @AuthenticationPrincipal
    UserDetailsImpl userDetails, @RequestBody UserEmailRequestDto requestDto) {

        userService.updateEmail(userId, userDetails.getUser(), requestDto);

        return ResponseEntity.status(200).body("수정 완료");
    }

    @PatchMapping("/{userId}/updatenickname")
    public ResponseEntity<?> updateNickname(@PathVariable Long userId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody UserNicknameDto requestDto) {

        userService.updateNickname(userId, userDetails.getUser(), requestDto);

        return ResponseEntity.status(200).body("수정 완료");
    }

    @PatchMapping("/{userId}/updatepassword")
    public ResponseEntity<?> updatePassword(@PathVariable Long userId,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody UserPasswordDto requestDto) {

        userService.updatePassword(userId, userDetails.getUser(), requestDto);

        return ResponseEntity.status(200).body("수정 완료");
    }
}
