package org.restlet.example.contact.api.core.exception;

/**
 * @author Manuel Boillod
 */
public class PathVariableRequiredException extends BadParameterException {

    public PathVariableRequiredException(String queryParameterName) {
        super("path variable '" + queryParameterName + "' is required");
    }
}
