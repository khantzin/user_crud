package com.kz.bookingsystem.exception;

import org.springframework.http.HttpStatus;

public class CoreApiException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String code;
    private String title;
    private String message;
    private Throwable cause;
    private HttpStatus status;

    public CoreApiException(String message) {
        super(message);
    }

    public CoreApiException(Throwable cause) {
        super(cause);
    }

    public CoreApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public CoreApiException(String title, String code, String message) {
        super(message);
        this.title = title;
        this.message = message;
        this.code = code;
    }

    public CoreApiException(String title, String code, String message, Throwable cause, HttpStatus status) {
        super();
        this.code = code;
        this.title = title;
        this.message = message;
        this.cause = cause;
        this.status = status;
    }

    public CoreApiException(String message, String code) {
        super();
        this.code = code;
        this.message = message;
    }

    private String getDetailMessage(String err) {
        String ERROR_KEY_MSG = "duplicate key value violates unique constraint";
        if (err != null) {
            if (err.contains(ERROR_KEY_MSG)) return "Duplicate Data exist";
            else return err;
        }
        return err;
    }


    public final String getCode() {
        return code;
    }

    public final void setCode(String code) {
        this.code = code;
    }

    public final String getTitle() {
        return title;
    }

    public final void setTitle(String title) {
        this.title = title;
    }

    public final String getMessage() {
        return getDetailMessage(message);
    }

    public final void setMessage(String message) {
        this.message = message;
    }

    public final Throwable getCause() {
        return cause;
    }

    public final void setCause(Throwable cause) {
        this.cause = cause;
    }

    public final HttpStatus getStatus() {
        return status;
    }

    public final void setStatus(HttpStatus status) {
        this.status = status;
    }

}
