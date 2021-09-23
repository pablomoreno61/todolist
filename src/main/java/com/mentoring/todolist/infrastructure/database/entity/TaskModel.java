package com.mentoring.todolist.infrastructure.database.entity;

import com.mentoring.todolist.domain.entity.TodoList.Task.Priority;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="tasks")
public class TaskModel {
    @Id
    private UUID id;
    private String name;
    private Priority priority;
    private ZonedDateTime completedAt;
    private boolean completed = false;
}
