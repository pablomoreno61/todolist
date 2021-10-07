package com.mentoring.todolist.infrastructure.database.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CreateTodoListRequest
{
    private String name;
}
