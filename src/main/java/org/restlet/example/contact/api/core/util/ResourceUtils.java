package org.restlet.example.contact.api.core.util;

import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.exception.BadParameterException;
import org.restlet.resource.ServerResource;

/**
 * @author Manuel Boillod
 */
public class ResourceUtils {

    public static Integer getPathVariableAsInteger(ServerResource serverResource, String pathVariableName) {
        String pathVariableValue = serverResource.getAttribute(pathVariableName);
        if (pathVariableValue == null) {
            throw new RuntimeException("Path variable '" + pathVariableValue + "' is null.");
        }
        try {
            return Integer.parseInt(pathVariableValue);
        } catch (NumberFormatException e) {
            throw new BadParameterException("Path variable '" + pathVariableValue + "' should be an integer.");
        }
    }

    public static void notNull(Object entity) {
        if (entity == null) {
            throw new BadEntityException("No input entity");
        }
    }
}
