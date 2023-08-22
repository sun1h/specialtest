package com.speical.project.data.controller;

import com.speical.project.data.dto.GameResultRequest;
import com.speical.project.data.service.DataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@Tag(name = "Data", description = "데이터값 관련 api")
@RequestMapping("/api/data")
public class DataController {

    private final DataService dataService;


    @GetMapping("/{userId}")

    @Operation(summary = "유저 데이터 조회", description = "조회하고 싶은 유저의 userId값을 입력해주세요.")
    public ResponseEntity<?> getUserData(@PathVariable Long userId, HttpServletRequest request){

        return ResponseEntity.ok(dataService.getUserData(userId));
    }

    @PutMapping("/result")
//    @ApiOperation(value = "게임 결과 데이터 업데이트")
    @Operation(summary = "게임 결과 데이터 업데이트", description = "게임에 참여한 유저의 userId값과 승리유무를 입력해주세요.")
    public ResponseEntity<?> updateResultData(@RequestBody GameResultRequest resultDto, HttpServletRequest request){

        return ResponseEntity.ok(dataService.updateResultData(resultDto));
    }
}
