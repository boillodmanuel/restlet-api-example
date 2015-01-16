package org.restlet.example.contact.api.core.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.restlet.example.contact.api.core.validation.FieldError;
import org.restlet.example.contact.api.core.validation.ValidationErrors;
import org.restlet.resource.Status;

import java.util.List;

/**
 * @author Manuel Boillod
 */
@Status(422)
public class BadEntityException extends BusinessException {

    List<String> globalMessages;

    List<FieldError> fieldErrors;

    public BadEntityException(String message) {
        super(422, message);
    }

    public BadEntityException(String message, ValidationErrors validationErrors) {
        this(message);
        this.globalMessages = validationErrors.getGlobalMessages();
        this.fieldErrors = validationErrors.getFieldErrors();
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<String> getGlobalMessages() {
        return globalMessages;
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }
}
