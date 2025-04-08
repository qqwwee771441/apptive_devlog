package apptive.devlog.global.response.error.exception;

import apptive.devlog.global.response.error.model.ErrorCode;

public class InvalidProviderException extends CustomException {
    public InvalidProviderException() {
        super(ErrorCode.INVALID_PROVIDER);
    }
}
