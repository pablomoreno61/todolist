package com.mentoring.todolist.domain.usecase;

import com.mentoring.todolist.domain.entity.TodoList;
import com.mentoring.todolist.domain.repository.TodoListRepository;

public class CreateTodoList {
    private final TodoListRepository repository;

    public CreateTodoList(TodoListRepository repository) {
        this.repository = repository;
    }

    public CreateTodoListResponse execute(CreateTodoListRequest request) {
        TodoList todoList = new TodoList(request.getName());

        repository.save(todoList);

        return new CreateTodoListResponse(
            todoList.getId(),
            todoList.getName(),
            todoList.getCreatedAt()
        );
    }
}
