package com.mentoring.todolist.infrastructure.cli.exception;

public class UnknownCliActionException extends Exception {
    private UnknownCliActionException(String message) {
        super(message);
    }

    public static UnknownCliActionException withInvalidAction(String action, String[] actions) {
        return new UnknownCliActionException(
            String.format(
                "Invalid action %s given. Use one of the following: %s", action, String.join(",", actions)
            )
        );
    }
}
