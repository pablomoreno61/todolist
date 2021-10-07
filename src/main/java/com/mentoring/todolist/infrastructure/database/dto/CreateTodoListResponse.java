package com.mentoring.todolist.infrastructure.database.dto;

import com.mentoring.todolist.domain.usecase.CreateTodoListOutput;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class CreateTodoListResponse
{
    private final UUID id;
    private final String name;
    private final ZonedDateTime createdAt;

    public static CreateTodoListResponse fromCreateTodoListOutput(
        CreateTodoListOutput createTodoListOutput
    ) {
        return CreateTodoListResponse.builder()
            .id(createTodoListOutput.getId())
            .name(createTodoListOutput.getName())
            .createdAt(createTodoListOutput.getCreatedAt())
            .build();
    }
}
