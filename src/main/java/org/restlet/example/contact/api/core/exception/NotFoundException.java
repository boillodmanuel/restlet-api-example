package org.restlet.example.contact.api.core.exception;

import org.restlet.resource.Status;

/**
 * @author Manuel Boillod
 */
@Status(404)
public class NotFoundException extends BusinessException {

    public NotFoundException(String message) {
        super(404, message);
    }
}
