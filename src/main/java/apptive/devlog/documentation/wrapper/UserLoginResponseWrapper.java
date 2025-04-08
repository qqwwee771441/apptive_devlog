package apptive.devlog.documentation.wrapper;

import apptive.devlog.common.response.success.CommonResponse;
import apptive.devlog.domain.auth.dto.UserLoginResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UserLoginResponseWrapper")
public class UserLoginResponseWrapper extends CommonResponse<UserLoginResponseDto> {}