package com.speical.project.global.common.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ResponseSuccessDto<T> extends ResponseDto {

	private T data;

	@Builder
	public ResponseSuccessDto(ZonedDateTime timeStamp, HttpStatus httpStatus, T data) {
		super(timeStamp, httpStatus.value(), httpStatus.getReasonPhrase());
		this.data = data;
	}

}