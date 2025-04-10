package apptive.devlog.domain.auth.controller;

import apptive.devlog.common.response.success.CommonResponse;
import apptive.devlog.documentation.auth.AuthLoginDoc;
import apptive.devlog.documentation.auth.AuthLogoutDoc;
import apptive.devlog.documentation.auth.AuthRefreshDoc;
import apptive.devlog.documentation.auth.AuthSignupDoc;
import apptive.devlog.documentation.tags.AuthDocumentation;
import apptive.devlog.domain.auth.dto.*;
import apptive.devlog.domain.auth.service.AuthService;
import apptive.devlog.global.annotation.InjectToken;
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

    @AuthSignupDoc
    @PostMapping("/signup")
    public ResponseEntity<CommonResponse<UserSignupResponseDto>> signup(@Valid @RequestBody UserSignupRequestDto requestDto) {
        UserSignupResponseDto responseDto = authService.signup(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(CommonResponse.created(responseDto));
    }

    @AuthLoginDoc
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<UserLoginResponseDto>> login(@Valid @RequestBody UserLoginRequestDto requestDto) {
        UserLoginResponseDto responseDto = authService.login(requestDto);
        return ResponseEntity.ok(CommonResponse.ok(responseDto));
    }

    @AuthRefreshDoc
    @PostMapping("/refresh")
    public ResponseEntity<CommonResponse<UserRefreshResponseDto>> refresh(@Valid @InjectToken UserRefreshRequestDto requestDto) {
        UserRefreshResponseDto responseDto = authService.refresh(requestDto);
        return ResponseEntity.ok(CommonResponse.ok(responseDto));
    }

    @AuthLogoutDoc
    @PostMapping("/logout")
    public ResponseEntity<CommonResponse<Void>> logout(@Valid @InjectToken UserLogoutRequestDto requestDto) {
        authService.logout(requestDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(CommonResponse.noContent());
    }
}
