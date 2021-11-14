package com.mentoring.todolist.domain.usecase;

import com.mentoring.todolist.domain.UUIDProvider;
import com.mentoring.todolist.domain.entity.TodoList;
import com.mentoring.todolist.domain.repository.TodoListRepository;

public class CreateTodoList {
    private final UUIDProvider uuidProvider;
    private final TodoListRepository repository;

    public CreateTodoList(
            UUIDProvider uuidProvider,
            TodoListRepository repository
    ) {
        this.uuidProvider = uuidProvider;
        this.repository = repository;
    }

    public CreateTodoListOutput execute(CreateTodoListInput input) {
        TodoList todoList = new TodoList(uuidProvider.uuid(), input.getName());

        repository.save(todoList);

        return new CreateTodoListOutput(
            todoList.getId(),
            todoList.getName(),
            todoList.getCreatedAt()
        );
    }
}
