package com.example.todobackend;

import com.example.todobackend.data.TodoEntity;
import com.example.todobackend.model.TodoModel;
import com.example.todobackend.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

@WebMvcTest
public class TodoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private TodoService todoservice;
    int id = 0;
    TodoModel todoModel = new TodoModel(id, "todo", 0, false)
    private void shouldCreateTodoGivenValidCreationRequest(){TodoModel todo= new todo(id, "todo", 0, false)
        when(todoservice.create(any())).thenreturn(todo)

        TodoDto tododto = new TodoModel(TodoEntity todo, "http://localhost/todos/" + id);
        mockMvc.perform("/todos").contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.WriteValueAsString(new CreateTodoDto("todo"))))
                .andExpect(status.isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content.json())
                .content(objectMapper.WriteValueAsString(todoDto), true));

        verify(service).createToDo("todo");
        //verify no more interaction srgvice
    }
}


