package com.mentoring.todolist.domain.exception;

public class InvalidTodoListFormatException extends
    RuntimeException {
    private final String name;

    public InvalidTodoListFormatException(String message, String name)
    {
        super(message);
        this.name = name;
    }

    public static InvalidTodoListFormatException withName(String name) {
        return new InvalidTodoListFormatException("Todo list name cannot be empty", name);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public String getName() {
        return name;
    }
}
