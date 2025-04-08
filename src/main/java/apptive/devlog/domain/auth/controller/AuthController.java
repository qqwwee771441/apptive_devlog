package apptive.devlog.domain.auth.controller;

import apptive.devlog.domain.auth.dto.*;
import apptive.devlog.global.annotation.InjectToken;
import apptive.devlog.domain.auth.service.AuthService;
import apptive.devlog.common.response.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<UserSignupResponseDto>> signup(@Valid @RequestBody UserSignupRequestDto requestDto) {
        UserSignupResponseDto responseDto = authService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.created(responseDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserLoginResponseDto>> login(@Valid @RequestBody UserLoginRequestDto requestDto) {
        UserLoginResponseDto responseDto = authService.login(requestDto);
        return ResponseEntity.ok(ApiResponse.ok(responseDto));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<UserRefreshResponseDto>> refresh(@Valid @InjectToken UserRefreshRequestDto requestDto) {
        UserRefreshResponseDto responseDto = authService.refresh(requestDto);
        return ResponseEntity.ok(ApiResponse.ok(responseDto));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@Valid @InjectToken UserLogoutRequestDto requestDto) {
        authService.logout(requestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(ApiResponse.noContent());
    }
}
