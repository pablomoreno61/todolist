package com.mentoring.todolist.infrastructure.rest.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.mentoring.todolist.infrastructure.dto.CreateTodoListRequest;
import com.mentoring.todolist.infrastructure.dto.CreateTodoListResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CreateTodoListControllerTest
{
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp()
    {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder().rootUri(String.format("http://localhost:%s", port));
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    public void cannotCreateTodoList_whenNameIsEmpty()
    {
        CreateTodoListRequest createTodoListRequest = new CreateTodoListRequest();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Object> request = new HttpEntity<>(createTodoListRequest, headers);
        ResponseEntity<CreateTodoListResponse> result = restTemplate.postForEntity(
            "/todolist/add",
            request,
            CreateTodoListResponse.class
        );

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void createTodoList()
    {
        CreateTodoListRequest createTodoListRequest = new CreateTodoListRequest();
        String todolistName = "TODOLIST_NAME";
        createTodoListRequest.setName(todolistName);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        HttpEntity<Object> request = new HttpEntity<>(createTodoListRequest, headers);
        ResponseEntity<CreateTodoListResponse> result = restTemplate.postForEntity(
            "/todolist/add",
            request,
            CreateTodoListResponse.class
        );

        assertEquals(todolistName, result.getBody().getName());
        assertNotNull(result.getBody().getCreatedAt());
        assertNotNull(result.getBody().getId());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
