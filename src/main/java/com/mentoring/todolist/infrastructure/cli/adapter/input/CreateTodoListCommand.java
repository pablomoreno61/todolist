package com.mentoring.todolist.infrastructure.cli.adapter.input;

import lombok.Getter;

@Getter
public class CreateTodoListCommand {
    private String action;
    private String name;
}
