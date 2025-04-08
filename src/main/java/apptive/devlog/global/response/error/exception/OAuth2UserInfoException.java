package apptive.devlog.global.response.error.exception;

import apptive.devlog.global.response.error.model.ErrorCode;

public class OAuth2UserInfoException extends CustomException {
    public OAuth2UserInfoException(String message) {
        super(ErrorCode.OAUTH2_USERINFO_FAILED, message);
    }
}