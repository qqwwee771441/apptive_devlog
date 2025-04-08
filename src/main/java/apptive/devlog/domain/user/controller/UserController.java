package apptive.devlog.domain.user.controller;

import apptive.devlog.common.response.success.CommonResponse;
import apptive.devlog.documentation.tags.UserDocumentation;
import apptive.devlog.domain.user.dto.UserProfileRequestDto;
import apptive.devlog.domain.user.dto.UserProfileResponseDto;
import apptive.devlog.domain.user.service.UserService;
import apptive.devlog.global.annotation.InjectEmail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User", description = "유저 관련 API")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController implements UserDocumentation {

    private final UserService userService;

    @Operation(summary = "유저 프로필 조회", description = "이메일을 기반으로 유저 프로필 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))
            )
    })
    @GetMapping("/profile")
    public ResponseEntity<CommonResponse<UserProfileResponseDto>> getUserProfile(@Parameter(description = "조회할 유저의 이메일", example = "test@example.com") @Valid @InjectEmail UserProfileRequestDto requestDto) {
        UserProfileResponseDto responseDto = userService.getUserProfile(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(CommonResponse.ok(responseDto));
    }
}
