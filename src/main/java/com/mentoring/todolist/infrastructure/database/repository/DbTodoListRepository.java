package com.mentoring.todolist.infrastructure.database.repository;

import com.mentoring.todolist.domain.entity.TodoList;
import com.mentoring.todolist.domain.repository.TodoListRepository;
import com.mentoring.todolist.infrastructure.database.entity.TaskModel;
import com.mentoring.todolist.infrastructure.database.entity.TodoListModel;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.jpa.repository.JpaRepository;

// TODO Create custom mapper or reuse an existing one (https://www.baeldung.com/java-performance-mapping-frameworks)
public class DbTodoListRepository implements TodoListRepository {
    private final JpaTodoListRepository jpaTodoListRepository;

    public DbTodoListRepository(
        JpaTodoListRepository jpaTodoListRepository
    ) {
        this.jpaTodoListRepository = jpaTodoListRepository;
    }

    @Override
    public void save(TodoList todoList) {
        // TODO refactor loop
        List<TaskModel> taskModels = todoList.getTasks().stream().map(
            t -> {return TaskModel.builder()
                .id(t.getId())
                .name(t.getName())
                .priority(t.getPriority())
                .completed(t.isCompleted())
                .completedAt(t.getCompletedAt())
                .build();}
        ).collect(Collectors.toList());

        TodoListModel todoListModel = TodoListModel.builder()
            .id(todoList.getId())
            .createdAt(todoList.getCreatedAt())
            .name(todoList.getName())
            .tasks(taskModels)
            .build();

        jpaTodoListRepository.save(todoListModel);
    }

    public void get() {
        // TODO Create custom mapper or reuse an existing one (https://www.baeldung.com/java-performance-mapping-frameworks)
    }
}
