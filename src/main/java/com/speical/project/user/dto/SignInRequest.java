package com.speical.project.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class SignInRequest {

    private String loginId;
    private String password;

}
