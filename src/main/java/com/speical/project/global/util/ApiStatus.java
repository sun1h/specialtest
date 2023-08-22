package com.speical.project.global.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ApiStatus {

	// Successful 2xx
	OK(HttpStatus.OK, "요청이 성공적으로 되었습니다.", true),
	CREATED(HttpStatus.CREATED, "리소스 생성 요청이 성공적으로 되었습니다.", true),
	ACCEPTED(HttpStatus.ACCEPTED, "요청을 수신했지만 그에 일지하는 응답을 할 수 없습니다.", true),
	NO_CONTENT(HttpStatus.NO_CONTENT, "요청을 성공적으로 완료했지만 제공하는 컨텐츠가 없습니다.", true),

	// Redirection 3xx

	// Client Error 4xx
	BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", false),
	INVALID_PARAMS(HttpStatus.BAD_REQUEST, "필수데이터 누락, 또는 형식과 다른 데이터를 요청했습니다.", false),
//	KEYWORD_EMPTY(HttpStatus.BAD_REQUEST, "검색 키워드가 비었습니다.", false),
//	KEYWORD_LESS_THAN_TWO(HttpStatus.BAD_REQUEST, "검색 키워드가 2자 이하입니다.", false),
	INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.", false),
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증 정보가 유효하지 않습니다.", false),
	FORBIDDEN(HttpStatus.FORBIDDEN, "페이지 열람 권한이 없습니다.", false),
	PAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 페이지입니다.", false),
	RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 리소스입니다.", false),
	CONFLICT(HttpStatus.CONFLICT, "클라이언트의 요청에서 충돌이 감지되었습니다.", false),
	DUPLICATION(HttpStatus.CONFLICT, "요청 값이 중복되었습니다.", false),
	ID_DUPLICATION(HttpStatus.CONFLICT, "이미 가입된 아이디입니다.", false),
	NICKNAME_DUPLICATION(HttpStatus.CONFLICT, "이미 사용중인 닉네임입니다.", false),


	// Server Error 5xx
	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러입니다.", false);

	private HttpStatus status;
	private String message;
	private boolean boolType;
}
