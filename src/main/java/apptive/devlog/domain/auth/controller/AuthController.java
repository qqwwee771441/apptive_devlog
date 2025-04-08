package apptive.devlog.domain.auth.controller;

import apptive.devlog.common.response.success.CommonResponse;
import apptive.devlog.documentation.tags.AuthDocumentation;
import apptive.devlog.documentation.wrapper.UserLoginResponseWrapper;
import apptive.devlog.documentation.wrapper.UserRefreshResponseWrapper;
import apptive.devlog.documentation.wrapper.UserSignupResponseWrapper;
import apptive.devlog.domain.auth.dto.*;
import apptive.devlog.domain.auth.service.AuthService;
import apptive.devlog.global.annotation.InjectToken;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthDocumentation {
    private final AuthService authService;

    @Operation(summary = "회원 가입", description = "회원으로 가입합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "회원가입 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserSignupResponseWrapper.class)
                    )),
            @ApiResponse(responseCode = "400", description = "요청 파라미터 오류 또는 중복된 이메일",
                    content = @Content(schema = @Schema(implementation = CommonResponse.class)))
    })
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<UserSignupResponseDto>> signup(@Valid @RequestBody UserSignupRequestDto requestDto) {
        UserSignupResponseDto responseDto = authService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.created(responseDto));
    }

    @Operation(summary = "회원 로그인", description = "이메일과 비밀번호로 로그인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserLoginResponseWrapper.class)
                    )),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<UserLoginResponseDto>> login(@Valid @RequestBody UserLoginRequestDto requestDto) {
        UserLoginResponseDto responseDto = authService.login(requestDto);
        return ResponseEntity.ok(CommonResponse.ok(responseDto));
    }

    @Operation(summary = "토큰 재발급", description = "Refresh Token을 이용하여 Access Token을 재발급합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "토큰 재발급 성공",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UserRefreshResponseWrapper.class)
                    )),
            @ApiResponse(responseCode = "401", description = "유효하지 않은 토큰", content = @Content)
    })
    @PostMapping("/refresh")
    public ResponseEntity<CommonResponse<UserRefreshResponseDto>> refresh(@Valid @InjectToken UserRefreshRequestDto requestDto) {
        UserRefreshResponseDto responseDto = authService.refresh(requestDto);
        return ResponseEntity.ok(CommonResponse.ok(responseDto));
    }

    @Operation(summary = "로그아웃", description = "로그아웃 처리합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "로그아웃 성공", content = @Content)
    })
    @PostMapping("/logout")
    public ResponseEntity<CommonResponse<Void>> logout(@Valid @InjectToken UserLogoutRequestDto requestDto) {
        authService.logout(requestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(CommonResponse.noContent());
    }
}
