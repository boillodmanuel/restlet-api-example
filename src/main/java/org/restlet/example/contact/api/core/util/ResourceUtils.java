package org.restlet.example.contact.api.core.util;

import org.restlet.example.contact.api.core.exception.BadEntityException;
import org.restlet.example.contact.api.core.exception.BadParameterException;
import org.restlet.example.contact.api.core.exception.PathVariableRequiredException;
import org.restlet.resource.ServerResource;

/**
 * @author Manuel Boillod
 */
public class ResourceUtils {

    public static String getRequiredPathVariable(ServerResource serverResource, String pathVariableName) {
        String pathVariableValue = serverResource.getQueryValue(pathVariableName);
        if (pathVariableValue == null) {
            throw new PathVariableRequiredException(pathVariableName);
        }
        return null;
    }
    public static Integer getRequiredPathVariableAsInteger(ServerResource serverResource, String pathVariableName) {
        String pathVariableValue = getRequiredPathVariable(serverResource, pathVariableName);
        try {
            return Integer.parseInt(pathVariableValue);
        } catch (NumberFormatException e) {
            throw new BadParameterException("Path variable '" + pathVariableValue + "' shoud be an integer.");
        }
    }

    public static void notNull(Object entity) {
        if (entity == null) {
            throw new BadEntityException("No input entity");
        }
    }
}
