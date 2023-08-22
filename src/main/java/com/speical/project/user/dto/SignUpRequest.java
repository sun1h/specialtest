package com.speical.project.user.dto;


import com.speical.project.data.entity.DataEntity;
import com.speical.project.user.entity.UserEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpRequest {

    private String loginId;
    private String password;
    private String nickname;
    @Schema(example = "1")
    private int avatar;

    public UserEntity toEntity(DataEntity data){
        return UserEntity.builder()
                .data(data)
                .loginId(this.loginId)
                .password(this.password)
                .nickname(this.nickname)
                .avatar(this.avatar)
                .weight(10)
                .build();
    }
}
