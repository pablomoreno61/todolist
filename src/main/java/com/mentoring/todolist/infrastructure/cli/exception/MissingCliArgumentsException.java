package com.mentoring.todolist.infrastructure.cli.exception;

public class MissingCliArgumentsException extends RuntimeException {
    private MissingCliArgumentsException(String message) {
        super(message);
    }

    public static MissingCliArgumentsException withInvalidAction(String action, String[] actions) {
        return new MissingCliArgumentsException(
            String.format(
                "Not enough arguments %s given. Use one of the following: %s", action, String.join(",", actions)
            )
        );
    }
}
