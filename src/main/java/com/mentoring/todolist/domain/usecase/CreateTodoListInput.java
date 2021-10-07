package com.mentoring.todolist.domain.usecase;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateTodoListInput {
    private String name;

    public String getName() {
        return name;
    }
}
