package com.mentoring.todolist.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import com.mentoring.todolist.domain.UUIDProvider;
import com.mentoring.todolist.domain.entity.TodoList;
import com.mentoring.todolist.domain.exception.InvalidTodoListFormatException;
import com.mentoring.todolist.domain.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import java.util.UUID;

// TODO Create todo list use case Unit test
public class CreateTodoListTest {

    private static final String TODOLIST_SAMPLE_NAME = "TODOLIST_SAMPLE";
    private static final UUID TODOLIST_UUID = UUID.randomUUID();

    private CreateTodoList createTodoListUseCase;

    @Mock
    private UUIDProvider uuidProvider;

    @Mock
    private TodoListRepository repository;

    @Captor
    ArgumentCaptor<TodoList> todoListCaptor;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        createTodoListUseCase = new CreateTodoList(uuidProvider, repository);
    }

    @Test
    public void shouldSaveTodoList() throws InvalidTodoListFormatException {
        when(uuidProvider.uuid()).thenReturn(TODOLIST_UUID);

        CreateTodoListInput todoListRequest = new CreateTodoListInput(TODOLIST_SAMPLE_NAME);
        createTodoListUseCase.execute(todoListRequest);

        verify(repository).save(todoListCaptor.capture());
        TodoList todoListCaptorValue = todoListCaptor.getValue();

        assertEquals(TODOLIST_SAMPLE_NAME, todoListCaptorValue.getName());
        assertEquals(TODOLIST_UUID, todoListCaptorValue.getId());
        assertNotNull(todoListCaptorValue.getCreatedAt());
    }

    @Test
    public void shouldReturnList() throws InvalidTodoListFormatException {
        when(uuidProvider.uuid()).thenReturn(TODOLIST_UUID);

        CreateTodoListInput todoListRequest = new CreateTodoListInput(TODOLIST_SAMPLE_NAME);
        CreateTodoListOutput todoListResponse = createTodoListUseCase.execute(todoListRequest);

        verify(repository).save(todoListCaptor.capture());
        TodoList todoListCaptorValue = todoListCaptor.getValue();

        assertEquals(TODOLIST_UUID, todoListResponse.getId());
        assertEquals(TODOLIST_SAMPLE_NAME, todoListResponse.getName());
        assertNotNull(todoListResponse.getCreatedAt());
        assertEquals(todoListCaptorValue.getCreatedAt(), todoListResponse.getCreatedAt());
    }
}
