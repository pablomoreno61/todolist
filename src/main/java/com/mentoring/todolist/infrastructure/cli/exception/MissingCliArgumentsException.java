package com.mentoring.todolist.infrastructure.cli.exception;

public class MissingCliArgumentsException extends Exception {
    public MissingCliArgumentsException(String message) {
        super(message);
    }
}
