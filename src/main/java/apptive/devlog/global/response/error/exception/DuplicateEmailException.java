package apptive.devlog.global.response.error.exception;

import apptive.devlog.global.response.error.model.ErrorCode;

public class DuplicateEmailException extends CustomException {
    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL);
    }
}
