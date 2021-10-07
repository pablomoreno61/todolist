package com.mentoring.todolist.domain.usecase;

import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateTodoListOutput {
    private final UUID id;
    private final String name;
    private final ZonedDateTime createdAt;
}
