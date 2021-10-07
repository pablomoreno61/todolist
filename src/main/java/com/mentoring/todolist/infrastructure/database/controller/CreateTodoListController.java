package com.mentoring.todolist.infrastructure.database.controller;

import com.mentoring.todolist.domain.usecase.CreateTodoList;
import com.mentoring.todolist.domain.usecase.CreateTodoListInput;
import com.mentoring.todolist.domain.usecase.CreateTodoListOutput;
import com.mentoring.todolist.infrastructure.database.dto.CreateTodoListRequest;
import com.mentoring.todolist.infrastructure.database.dto.CreateTodoListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todolist")
public class CreateTodoListController
{
    private final CreateTodoList createTodoList;

    @Autowired
    public CreateTodoListController(
        CreateTodoList createTodoList
    ) {
        this.createTodoList = createTodoList;
    }

    // TODO Catch exception with an ExceptionHandler
    // TODO Catch size/format name exception
    @PostMapping("/add")
    public CreateTodoListResponse add(
        @RequestBody CreateTodoListRequest todoListRequestData
    ) {
        CreateTodoListInput todoListRequest = new CreateTodoListInput(
            todoListRequestData.getName()
        );

        CreateTodoListOutput createTodoListOutput = createTodoList.execute(todoListRequest);

        return CreateTodoListResponse.fromCreateTodoListOutput(createTodoListOutput);
    }
}
