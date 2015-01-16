package org.restlet.example.contact.api.core.exception;

import org.restlet.resource.Status;

/**
 * @author Manuel Boillod
 */
@Status(400)
public class BadParameterException extends BusinessException {

    public BadParameterException(String message) {
        super(400, message);
    }
}
