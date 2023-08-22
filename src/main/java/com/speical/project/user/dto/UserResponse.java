package com.speical.project.user.dto;

import com.speical.project.user.entity.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {

    private String loginId;
//    private Long userId;
    private String nickname;
    private int avatar;
    private int weight;

    public static UserResponse toDto(UserEntity user){
        return UserResponse.builder()
                .loginId(user.getLoginId())
//                .userId(user.getId())
                .nickname(user.getNickname())
                .avatar(user.getAvatar())
                .weight(user.getWeight())
                .build();

    }
}
