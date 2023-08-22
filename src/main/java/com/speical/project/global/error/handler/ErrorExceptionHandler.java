package com.speical.project.global.error.handler;


import com.speical.project.global.error.exception.ApiErrorException;
import com.speical.project.global.util.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ErrorExceptionHandler {

	private final ResponseUtil<String> responseUtil;

	/**
	 * Rest Api 에러를 핸들링 하기 위한 핸들러 메소드
	 *
	 * @param
	 * @return ResponseEntity&ltResponseErrorDto&gt
	 */
	@ExceptionHandler({ApiErrorException.class})
	private ResponseEntity<?> handle(HttpServletRequest request, final ApiErrorException e) {
		log.info("\nApi Error Log :" + request.getContextPath() + "\n" + e);

		return ResponseEntity
			.status(e.getException().getStatus())
			.body(responseUtil.buildErrorResponse(e.getException().getStatus(), e.getMessage(),
				request.getRequestURI()));
	}

	/**
	 * 서버 에러 (HttpStatus 5XX) 대응하기 위한 핸들러 메소드
	 *
	 * @param
	 * @return  ResponseEntity&ltResponseErrorDto&gt
	 */
	@ExceptionHandler({Exception.class})
	protected ResponseEntity<?> handleServerException(HttpServletRequest request, Exception e) {
		log.info("\nServer Error Log :" + request.getContextPath() + "\n" + e);

		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(responseUtil.buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러입니다.",
				request.getRequestURI()));
	}
}
