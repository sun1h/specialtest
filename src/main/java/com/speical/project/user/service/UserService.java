package com.speical.project.user.service;

import com.speical.project.global.util.*;
import com.speical.project.data.entity.DataEntity;
import com.speical.project.data.repository.DataRepository;
import com.speical.project.global.common.dto.ResponseSuccessDto;
import com.speical.project.global.error.exception.ApiErrorException;
import com.speical.project.global.util.ApiStatus;
import com.speical.project.user.dto.SignInRequest;
import com.speical.project.user.dto.SignUpRequest;
import com.speical.project.user.dto.UserResponse;
import com.speical.project.user.entity.UserEntity;
import com.speical.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) // 기본적으로 트랜잭션 안에서만 데이터 변경하게 설정(성능 향상)
@RequiredArgsConstructor // Lombok을 사용해 @Autowired 없이 의존성 주입. final 객제만 주입됨을 주의
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final ResponseUtil responseUtil;

    private final UserRepository userRepository;
    private final DataRepository dataRepository;


    @Transactional
    public ResponseSuccessDto<?> signUp(SignUpRequest userDto, Long userDataId){

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));

        DataEntity findUserData = dataRepository.findById(userDataId)
                .orElseThrow(() -> new ApiErrorException(ApiStatus.RESOURCE_NOT_FOUND));

        UserEntity user = userDto.toEntity(findUserData);
        userRepository.save(user);

        return responseUtil.buildSuccessResponse(null);
    }

    @Transactional
    public ResponseSuccessDto<?> signIn(SignInRequest signInDto){

        UserEntity findUser = userRepository.findByLoginId(signInDto.getLoginId())
                .orElseThrow(() -> new ApiErrorException(ApiStatus.RESOURCE_NOT_FOUND));

        if(!passwordEncoder.matches(signInDto.getPassword(), findUser.getPassword())){
            throw new ApiErrorException(ApiStatus.INVALID_PASSWORD);
        }

        return responseUtil.buildSuccessResponse(findUser.getId());

    }

    @Transactional
    public ResponseSuccessDto<?> getUser(Long userId){

        UserEntity findUser = userRepository.findById(userId)
                .orElseThrow(() -> new ApiErrorException(ApiStatus.RESOURCE_NOT_FOUND));

        UserResponse User = UserResponse.toDto(findUser);

        return responseUtil.buildSuccessResponse(User);
    }

    @Transactional
    public ResponseSuccessDto<?> checkId(String loginId){

        if(null != userRepository.findByLoginId(loginId)){
            throw new ApiErrorException(ApiStatus.ID_DUPLICATION);
        }

        return responseUtil.buildSuccessResponse(loginId);
    }

    @Transactional
    public ResponseSuccessDto<?> checkNickname(String nickname){

        if(null != userRepository.findByNickname(nickname)){
            throw new ApiErrorException(ApiStatus.NICKNAME_DUPLICATION);
        }

        return responseUtil.buildSuccessResponse(nickname);
    }

}
