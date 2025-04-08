package apptive.devlog.global.response.error.exception;

import apptive.devlog.global.response.error.model.ErrorCode;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
