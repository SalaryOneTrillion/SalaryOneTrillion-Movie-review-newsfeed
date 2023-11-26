package com.sparta.salaryonetrillionmoviereviewnewsfeed.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionCode {

    // CONFLICT
    CONFLICT_ID_EMAIL_NICKNAME_IN_USE(HttpStatus.CONFLICT, "사용자 아이디, 이메일 또는 닉네임이 이미 사용 중 입니다."),
    CONFLICT_EMAIL_IN_USE(HttpStatus.CONFLICT, "중복된 이메일 입니다."),
    CONFLICT_NICK_IN_USE(HttpStatus.CONFLICT, "중복된 닉네임 입니다."),

    // FORBIDDEN
    FORBIDDEN_EDIT_ONLY_EDITED(HttpStatus.FORBIDDEN, "작성자만 수정 할 수 있습니다."),
    FORBIDDEN_DELETE_ONLY_EDITED(HttpStatus.FORBIDDEN, "작성자만 삭제 할 수 있습니다."),
    FORBIDDEN_YOUR_NOT_COME_IN(HttpStatus.FORBIDDEN, "권한이 없습니다."),

    // NOT_FOUND
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "해당 유저는 존재하지 않습니다."),
    NOT_FOUND_MOVIE(HttpStatus.NOT_FOUND, "해당 영화는 존재하지 않습니다."),
    NOT_FOUND_REVIEW(HttpStatus.NOT_FOUND, "해당 리뷰는 존재하지 않습니다."),
    NOT_FOUND_REVIEW_COMMENT(HttpStatus.NOT_FOUND, "해당 댓글은 존재하지 않습니다."),

    // BAD_REQUEST
    BAD_REQUEST_ALREADY_EDITED_REVIEW(HttpStatus.BAD_REQUEST, "이미 리뷰를 작성 하셨습니다."),
    BAD_REQUEST_NOT_MATCH_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
