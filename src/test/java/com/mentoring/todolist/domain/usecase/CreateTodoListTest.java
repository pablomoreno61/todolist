package com.mentoring.todolist.domain.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

import com.mentoring.todolist.domain.entity.TodoList;
import com.mentoring.todolist.domain.exception.InvalidTodoListFormatException;
import com.mentoring.todolist.domain.repository.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

// TODO Create todo list use case Unit test
public class CreateTodoListTest {

    public static final String TODOLIST_SAMPLE_NAME = "TODOLIST_SAMPLE";
    private CreateTodoList createTodoListUseCase;

    @Mock
    private TodoListRepository repository;

    @Captor
    ArgumentCaptor<TodoList> todoListCaptor;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        createTodoListUseCase = new CreateTodoList(repository);
    }

    @Test
    public void executeTest() throws InvalidTodoListFormatException {
        CreateTodoListInput todoListRequest = new CreateTodoListInput(TODOLIST_SAMPLE_NAME);
        CreateTodoListOutput todoListResponse = createTodoListUseCase.execute(todoListRequest);

        verify(repository).save(todoListCaptor.capture());
        TodoList todoListCaptorValue = todoListCaptor.getValue();

        assertEquals(TODOLIST_SAMPLE_NAME, todoListCaptorValue.getName());
        assertEquals(todoListCaptorValue.getId(), todoListResponse.getId());
        assertEquals(todoListCaptorValue.getCreatedAt(), todoListResponse.getCreatedAt());

        assertNotNull(todoListResponse.getId());
        assertNotNull(todoListResponse.getCreatedAt());
        assertEquals(TODOLIST_SAMPLE_NAME, todoListResponse.getName());
    }
}
