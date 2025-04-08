package apptive.devlog.global.response.error.exception;

import apptive.devlog.global.response.error.model.ErrorCode;

public class InvalidEmailOrPasswordException extends CustomException {
    public InvalidEmailOrPasswordException() {
        super(ErrorCode.INVALID_EMAIL_OR_PASSWORD);
    }
}