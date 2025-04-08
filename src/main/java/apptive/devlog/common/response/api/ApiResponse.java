package apptive.devlog.common.response.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {

    private final boolean success;
    private final int status;
    private final String message;
    private final T data;
    private final LocalDateTime timestamp;

    @Builder
    private ApiResponse(boolean success, HttpStatus httpStatus, String message, T data, LocalDateTime timestamp) {
        this.success = success;
        this.status = httpStatus.value();
        this.message = message;
        this.data = data;
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
    }

    // ===== Success =====

    public static <T> ApiResponse<T> success(HttpStatus status, String message, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .httpStatus(status)
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> success(HttpStatus status, T data) {
        return ApiResponse.<T>builder()
                .success(true)
                .httpStatus(status)
                .message("요청이 성공했습니다.")
                .data(data)
                .build();
    }

    public static ApiResponse<Void> success(HttpStatus status, String message) {
        return ApiResponse.<Void>builder()
                .success(true)
                .httpStatus(status)
                .message(message)
                .build();
    }

    public static ApiResponse<Void> success(HttpStatus status) {
        return ApiResponse.<Void>builder()
                .success(true)
                .httpStatus(status)
                .message("요청이 성공했습니다.")
                .build();
    }

    // 기본 200 OK
    public static <T> ApiResponse<T> ok(T data) {
        return success(HttpStatus.OK, data);
    }

    public static <T> ApiResponse<T> created(T data) {
        return success(HttpStatus.CREATED, data);
    }

    public static ApiResponse<Void> noContent() {
        return success(HttpStatus.NO_CONTENT, (Void) null);
    }

    // ===== Error =====

    public static ApiResponse<Void> error(HttpStatus status, String message) {
        return ApiResponse.<Void>builder()
                .success(false)
                .httpStatus(status)
                .message(message)
                .build();
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message, T data) {
        return ApiResponse.<T>builder()
                .success(false)
                .httpStatus(status)
                .message(message)
                .data(data)
                .build();
    }
}
