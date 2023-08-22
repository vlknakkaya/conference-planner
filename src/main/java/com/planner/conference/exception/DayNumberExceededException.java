package com.planner.conference.exception;

public class DayNumberExceededException extends RuntimeException {

    private final String message;

    public DayNumberExceededException(int totalDay, int requestedDay) {
        message = String.format("Day number was exceeded. Total days: %s, Requested day: %s", totalDay, requestedDay);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
