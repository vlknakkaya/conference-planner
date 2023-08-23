package com.planner.conference.util;

public final class ErrorCodes {

    public static final int ENTITY_NOT_FOUND = 101;
    public static final int DAY_NUMBER_EXCEEDED = 102;
    public static final int DATA_INTEGRITY_ERROR = 103;
    public static final int VALIDATION_ERROR = 400;
    public static final int VALIDATION_EMPTY_NAME = 401;
    public static final int VALIDATION_BOTH_DURATION_AND_LIGHTNING = 402;
    public static final int VALIDATION_EMPTY_DURATION_AND_LIGHTNING = 403;
    public static final int VALIDATION_DURATION_LOWER_THAN_ZERO = 404;
    public static final int VALIDATION_LIGHTNING_MUST_BE_TRUE = 405;

    private ErrorCodes() {
        throw new IllegalStateException("Util class");
    }

}