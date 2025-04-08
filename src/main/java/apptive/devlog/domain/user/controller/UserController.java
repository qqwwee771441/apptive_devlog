package apptive.devlog.domain.user.controller;

import apptive.devlog.global.annotation.InjectEmail;
import apptive.devlog.domain.user.dto.UserProfileRequestDto;
import apptive.devlog.domain.user.dto.UserProfileResponseDto;
import apptive.devlog.domain.user.service.UserService;
import apptive.devlog.global.response.api.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserProfileResponseDto>> getUserProfile(@Valid @InjectEmail UserProfileRequestDto requestDto) {
        return ResponseEntity.ok(ApiResponse.success(userService.getUserProfile(requestDto)));
    }
}
