package com.mentoring.todolist.domain.exception;

public class InvalidTodoListFormatException extends
    Exception {
    private final String name;

    public InvalidTodoListFormatException(String message, String name)
    {
        super(message);
        this.name = name;
    }

    public static InvalidTodoListFormatException withName(String name) {
        return new InvalidTodoListFormatException("Todo list name length must be between 1 and 50", name);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public String getName() {
        return name;
    }
}
