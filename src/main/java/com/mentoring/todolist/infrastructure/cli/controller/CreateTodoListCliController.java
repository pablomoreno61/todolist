package com.mentoring.todolist.infrastructure.cli.controller;

import com.mentoring.todolist.domain.exception.InvalidTodoListFormatException;
import com.mentoring.todolist.domain.usecase.CreateTodoList;
import com.mentoring.todolist.domain.usecase.CreateTodoListInput;
import com.mentoring.todolist.domain.usecase.CreateTodoListOutput;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListRequest;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
        CreateTodoListRequest todoListRequestData
    ) {
        try {
            CreateTodoListInput todoListRequest = new CreateTodoListInput(
                todoListRequestData.getName()
            );

            CreateTodoListOutput createTodoListOutput;

            createTodoListOutput = createTodoList.execute(todoListRequest);

            return CreateTodoListResponse.fromCreateTodoListOutput(createTodoListOutput);
        } catch (InvalidTodoListFormatException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
