package com.planner.conference.exception;

public class EntityNotFoundException extends RuntimeException {

    private final String message;

    public EntityNotFoundException(String entityName, String fieldName, Object value) {
        message = String.format("%s not found with %s = %s", entityName, fieldName, value);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
