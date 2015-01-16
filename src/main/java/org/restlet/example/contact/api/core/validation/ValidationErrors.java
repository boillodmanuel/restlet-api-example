package org.restlet.example.contact.api.core.validation;

import org.restlet.example.contact.api.core.exception.BadEntityException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manuel Boillod
 */
public class ValidationErrors {

    List<String> globalMessages = new ArrayList<>();

    List<FieldError> fieldErrors = new ArrayList<>();

    public List<String> getGlobalMessages() {
        return globalMessages;
    }

    public List<FieldError> getFieldErrors() {
        return fieldErrors;
    }

    public void addGlobalMessage(String globalMessage) {
        globalMessages.add(globalMessage);
    }

    public void addFieldError(String field, String message) {
        addFieldError(new FieldError(field, message));
    }

    public void addFieldError(FieldError fieldError) {
        fieldErrors.add(fieldError);
    }

    public void checkErrors(String message) {
        if (!globalMessages.isEmpty() || !fieldErrors.isEmpty()) {
            throw new BadEntityException(message, this);
        }
    }
}
