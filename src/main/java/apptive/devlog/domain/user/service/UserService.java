package apptive.devlog.domain.user.service;

import apptive.devlog.domain.user.dto.UserProfileRequestDto;
import apptive.devlog.domain.user.dto.UserProfileResponseDto;
import apptive.devlog.domain.user.entity.User;
import apptive.devlog.domain.user.repository.UserRepository;
import apptive.devlog.global.response.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserProfileResponseDto getUserProfile(UserProfileRequestDto requestDto) {
        User user = userRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(UserNotFoundException::new);
        return new UserProfileResponseDto(user);
    }
}
