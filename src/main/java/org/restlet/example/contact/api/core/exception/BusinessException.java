package org.restlet.example.contact.api.core.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Manuel Boillod
 */
@JsonIgnoreProperties({"cause", "localizedMessage", "stackTrace", "suppressed"})
public abstract class BusinessException extends RuntimeException {

    int status;

    public BusinessException(int status, String message) {
        super(message);
        this.status = status;
    }

    public BusinessException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
