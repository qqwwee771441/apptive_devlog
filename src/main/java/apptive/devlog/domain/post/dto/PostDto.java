package apptive.devlog.domain.post.dto;

import apptive.devlog.domain.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

public class PostDto {
    @Builder
    public record Create(@NotBlank String title, @NotBlank String content) {}

    @Builder
    public record Update(@NotBlank String title, @NotBlank String content) {}

    @Builder
    public record Response(String id, String title, String content, String authorId) {}
}
