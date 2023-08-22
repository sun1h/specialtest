package com.speical.project.data.service;

import com.speical.project.data.dto.DataResponse;
import com.speical.project.data.dto.GameResultRequest;
import com.speical.project.global.util.*;
import com.speical.project.data.entity.DataEntity;
import com.speical.project.data.repository.DataRepository;
import com.speical.project.global.common.dto.ResponseSuccessDto;
import com.speical.project.global.error.exception.ApiErrorException;
import com.speical.project.global.util.ApiStatus;
import com.speical.project.user.entity.UserEntity;
import com.speical.project.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional(readOnly = true) // 기본적으로 트랜잭션 안에서만 데이터 변경하게 설정(성능 향상)
@RequiredArgsConstructor // Lombok을 사용해 @Autowired 없이 의존성 주입. final 객제만 주입됨을 주의
public class DataService {

    private final ResponseUtil responseUtil;

    private final DataRepository dataRepository;
    private final UserRepository userRepository;

    // 회원가입 시 유저에 대한 모든 기본데이터에 대한 정보 생성
    @Transactional
    public Long addUserData(){

        DataEntity saveUserData = DataEntity.builder().status_1(10).status_2(10).status_3(10).build();

        return dataRepository.save(saveUserData).getId();
    }


    // 유저 데이터 조회
    @Transactional
    public ResponseSuccessDto<?> getUserData(Long userId){

        UserEntity findUser = userRepository.findById(userId)
                .orElseThrow(() -> new ApiErrorException(ApiStatus.RESOURCE_NOT_FOUND));

        DataResponse UserData = DataResponse.toDto(findUser.getData());

        return responseUtil.buildSuccessResponse(UserData);

    }

    // 게임 결과 데이터를 업데이트
    @Transactional
    public ResponseSuccessDto<?> updateResultData(GameResultRequest resultDto){

        UserEntity findUser = userRepository.findById(resultDto.getUserId())
                .orElseThrow(() -> new ApiErrorException(ApiStatus.RESOURCE_NOT_FOUND));

        DataEntity findData = dataRepository.findByUser(findUser)
                .orElseThrow(() -> new ApiErrorException(ApiStatus.RESOURCE_NOT_FOUND));


        DataEntity saveData = resultDto.toDataEntity(findData);

        dataRepository.save(saveData).getId();

        return responseUtil.buildSuccessResponse(null);
    }
}
