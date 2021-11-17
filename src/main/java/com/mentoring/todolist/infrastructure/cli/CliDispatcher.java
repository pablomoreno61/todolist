package com.mentoring.todolist.infrastructure.cli;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mentoring.todolist.infrastructure.cli.exception.UnknownCliActionException;
import com.mentoring.todolist.infrastructure.cli.controller.CreateTodoListCliController;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListRequest;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CliDispatcher {
    private final String[] actions = new String[]{"create"};
    private final CreateTodoListCliController createTodoListCliController;

    public CliDispatcher(
        CreateTodoListCliController createTodoListCliController) {
        this.createTodoListCliController = createTodoListCliController;
    }

    public String manage(String action, String[] params) throws JsonProcessingException, ArrayIndexOutOfBoundsException {
        String managedResponse;

        Map<String, String> map = Arrays.stream(params)
            .map(v -> v.split("="))
            .collect(Collectors.toMap(a -> a[0], a -> a[1]));

        switch(action) {
            case "create":
                CreateTodoListRequest createTodoListRequest = new CreateTodoListRequest(map.get("name"));
                CreateTodoListResponse createTodoListResponse = createTodoListCliController.add(createTodoListRequest);

                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                managedResponse = objectMapper.writeValueAsString(createTodoListResponse);

                break;
            default:
                throw UnknownCliActionException.withInvalidAction(action, actions);
        }

        return managedResponse;
    }
}
