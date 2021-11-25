package com.mentoring.todolist.infrastructure.cli.controller;

import com.mentoring.todolist.domain.exception.InvalidTodoListFormatException;
import com.mentoring.todolist.domain.usecase.CreateTodoList;
import com.mentoring.todolist.domain.usecase.CreateTodoListInput;
import com.mentoring.todolist.domain.usecase.CreateTodoListOutput;
import com.mentoring.todolist.infrastructure.cli.adapter.input.CreateTodoListCommand;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateTodoListCliController
{
    private final CreateTodoList createTodoList;

    @Autowired
    public CreateTodoListCliController(
        CreateTodoList createTodoList
    ) {
        this.createTodoList = createTodoList;
    }

    public CreateTodoListResponse add(
        CreateTodoListCommand todoListRequestData
    ) {
        CreateTodoListResponse createTodoListResponse = null;

        try {
            CreateTodoListInput todoListInput = new CreateTodoListInput(
                todoListRequestData.getName()
            );

            CreateTodoListOutput createTodoListOutput;

            createTodoListOutput = createTodoList.execute(todoListInput);

            createTodoListResponse = CreateTodoListResponse.fromCreateTodoListOutput(
                createTodoListOutput);
        } catch (InvalidTodoListFormatException e) {
            System.out.println(e.getMessage());
        }

        return createTodoListResponse;
    }
}
