package com.speical.project.global.common.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseErrorDto<T> extends ResponseDto {

	private T error;

	private String path;

	@Builder
	public ResponseErrorDto(ZonedDateTime timeStamp, HttpStatus httpStatus, String path, T error) {
		super(timeStamp, httpStatus.value(), httpStatus.getReasonPhrase());
		this.error = error;
		this.path = path;
	}

}