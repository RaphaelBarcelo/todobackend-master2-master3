package com.example.todobackend;

import com.example.todobackend.domain.model.TodoModel;
import com.example.todobackend.domain.service.TodoService;
import com.example.todobackend.view.dto.TodoCreate;
import com.example.todobackend.view.dto.TodoDto;
import com.example.todobackend.view.dto.TodoUpdate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void shouldCreateTodoGivenValidCreationRequest() throws Exception {
        UUID id = UUID.fromString("b08d9932-4dd8-4652-b9b3-76f5f06261d4");
        TodoModel todoModel= new TodoModel(id, "todo", false, 0);
        when(todoService.create("todo")).thenReturn(todoModel);

        TodoDto todoDto = new TodoDto(todoModel, "http://localhost/todos/" + id);
        mockMvc.perform(post("/todos").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new TodoCreate("todo"))))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(todoDto),true));

        verify(todoService).create("todo");
        verifyNoMoreInteractions(todoService);
    }

    @Test
    public void shouldUpdateTodoGivenValidUpdateRequest() throws Exception {
        UUID id = UUID.fromString("b08d9932-4dd8-4652-b9b3-76f5f06261d4");
        String title = "todo";
        Boolean completed = false;
        int order = 0;
        TodoModel todoModel = new TodoModel(id, title, completed, order);
        TodoUpdate todoUpdate = new TodoUpdate(title, completed, order);
        when(todoService.update(id, title, completed, order)).thenReturn(Optional.of(todoModel));

        TodoDto todoDto = new TodoDto(todoModel, "http://localhost/todos/" + id);
        mockMvc.perform(patch("/todos/{id}", id).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todoUpdate)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(todoDto),true));

        verify(todoService).update(id, title, completed, order);
        verifyNoMoreInteractions(todoService);
    }
}