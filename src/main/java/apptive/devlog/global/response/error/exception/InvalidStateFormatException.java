package apptive.devlog.global.response.error.exception;

import apptive.devlog.global.response.error.model.ErrorCode;

public class InvalidStateFormatException extends CustomException {
    public InvalidStateFormatException() {
        super(ErrorCode.INVALID_STATE_FORMAT);
    }
}