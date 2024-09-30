package com.zerone.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(4004, "Uncategorized exception"),
    INVALID_KEY(1001, "Uncategorized exception"),
    USERNAME_EXISTED(1002, "Username existed."),
    USERNAME_NOT_EXISTED(1005, "Username not existed!"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters"),
    PASSWORD_INVALID(1004, "Password must be at least 8 characters")
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
