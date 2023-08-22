package com.speical.project.global.error.exception;


import com.speical.project.global.util.ApiStatus;
import lombok.Getter;

@Getter
public class ApiErrorException extends DefaultErrorException {

	private static final long serialVersionUID = 1L;

	private ApiStatus exception;

	public ApiErrorException(ApiStatus e) {
		super(e.getMessage());
		this.exception = e;
	}

}
