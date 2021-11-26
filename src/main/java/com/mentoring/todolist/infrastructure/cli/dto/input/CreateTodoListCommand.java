package com.mentoring.todolist.infrastructure.cli.dto.input;

import lombok.Getter;

@Getter
public class CreateTodoListCommand {
    private String action;
    private String name;
}
