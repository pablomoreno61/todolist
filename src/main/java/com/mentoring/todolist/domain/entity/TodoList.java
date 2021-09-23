package com.mentoring.todolist.domain.entity;

import com.mentoring.todolist.domain.entity.TodoList.Task.Priority;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.Getter;

@Getter
public class TodoList {
    private final UUID id;
    private final String name;
    private final ZonedDateTime createdAt;
    private List<Task> tasks;

    public TodoList(String name) {
        this.id = UUID.randomUUID();

        // Constraint size, format
        this.name = name;
        this.createdAt = ZonedDateTime.now();
    }

    public void addTask(String name, Priority priority) {
        Task task = new Task(name, priority);

        this.tasks.add(task);
    }

    public void completeTask(UUID id) {
        Optional<Task> optionalTask = this.tasks.stream().filter(t -> t.id.equals(id)).findFirst();
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.complete();
        }
    }

    @Getter
    public static class Task {
        public enum Priority {
            LOW, HIGH
        }

        public Task(String name, Priority priority) {
            this.id = UUID.randomUUID();
            this.name = name;
            this.priority = priority;
        }

        private final UUID id;
        private final String name;
        private final Priority priority;
        private ZonedDateTime completedAt;
        private boolean completed = false;

        public void complete() {
            this.completed = true;
            this.completedAt = ZonedDateTime.now();
        }
    }
}
