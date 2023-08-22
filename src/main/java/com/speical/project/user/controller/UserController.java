package com.speical.project.user.controller;

import com.speical.project.data.service.DataService;
import com.speical.project.global.util.ApiStatus;
import com.speical.project.user.dto.SignInRequest;
import com.speical.project.user.dto.SignUpRequest;
import com.speical.project.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "Member", description = "유저 관련 api")
public class UserController {

    private final UserService userService;
    private final DataService dataService;

    // 회원가입
    @Operation(summary = "회원 가입", description = "회원가입 메서드입니다."+"\n\n### [ 참고사항 ]\n\n"+"- 아이디 중복체크, 닉네임 중복체크 후 적합한 값을 넣어주세요\n\n")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "#### 성공"),
            @ApiResponse(responseCode = "에러", description = "#### 에러 이유를 확인 하십시오",
                    content =@Content(schema = @Schema(implementation = ApiStatus.class),
                    examples = { @ExampleObject(name="500", value ="닉네임과 아이디가 이미 존재하는지 확인해주세요")}))})
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpRequest signUpDto){
        Long dataId = dataService.addUserData();
        return ResponseEntity.ok(userService.signUp(signUpDto, dataId));
    }

    // 로그인
    @Operation(summary = "로그인", description = "아이디와 패스워드를 입력하여주세요.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "#### 성공"),
            @ApiResponse(responseCode = "에러", description = "#### 에러 이유를 확인 하십시오",
                    content =@Content(schema = @Schema(implementation = ApiStatus.class),
                            examples = { @ExampleObject(name="404", value ="유저가 존재하지 않습니다. 아이디를 확인해주세요."),
                                        @ExampleObject(name="400", value ="유저가 존재하지 않습니다. 비밀번호를 확인해주세요.") }))})
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInDto){
        return ResponseEntity.ok(userService.signIn(signInDto));
    }

    // 아이디 중복체크
    @Operation(summary = "아이디 중복체크", description = "회원가입전 아이디 중복체크하는 메서드입니다.")
    @PostMapping("/check/id/{loginId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "#### 성공"),
            @ApiResponse(responseCode = "에러", description = "#### 에러 이유를 확인 하십시오",
                    content =@Content(schema = @Schema(implementation = ApiStatus.class),
                            examples = { @ExampleObject(name="409", value ="이미 존재하는 아이디 입니다. 다른 값을 입력해주세요")}))})
    public ResponseEntity<?> checkId(@PathVariable String loginId){
        return ResponseEntity.ok(userService.checkId(loginId));
    }

    // 닉네임 중복체크
    @Operation(summary = "닉네임 중복체크", description = "회원가입전 닉네임 중복체크하는 메서드입니다.")
    @PostMapping("/check/nickname/{nickname}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "#### 성공"),
            @ApiResponse(responseCode = "에러", description = "#### 에러 이유를 확인 하십시오",
                    content =@Content(schema = @Schema(implementation = ApiStatus.class),
                            examples = { @ExampleObject(name="409", value ="이미 존재하는 닉네임 입니다. 다른 값을 입력해주세요")}))})
    public ResponseEntity<?> checkNickname(@PathVariable String nickname){
        return ResponseEntity.ok(userService.checkNickname(nickname));
    }

    //회원조회
    @Operation(summary = "유저 조회", description = "조회하고 싶은 유저의 userId값을 입력해주세요.")
    @GetMapping("/{userId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "#### 성공"),
            @ApiResponse(responseCode = "에러", description = "#### 에러 이유를 확인 하십시오",
                    content =@Content(schema = @Schema(implementation = ApiStatus.class),
                            examples = { @ExampleObject(name="404", value ="유저가 존재하지 않습니다. userid값을 확인해주세요.")}))})
    public ResponseEntity<?> getUser(@PathVariable Long userId, HttpServletRequest request){

        return ResponseEntity.ok(userService.getUser(userId));
    }

}
