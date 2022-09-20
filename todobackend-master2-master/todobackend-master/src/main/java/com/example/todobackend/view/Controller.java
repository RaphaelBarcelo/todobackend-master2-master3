package com.example.todobackend.view;

import com.example.todobackend.domain.model.TodoModel;
import com.example.todobackend.domain.service.TodoService;
import com.example.todobackend.view.dto.TodoCreate;
import com.example.todobackend.view.dto.TodoDto;
import com.example.todobackend.view.dto.TodoUpdate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
public class Controller {
    private final TodoService todoService;

    public Controller(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping //(method = RequestMethod.GET, value = "/")
    public List<TodoDto> getTodos(){
        return todoService.getAll().stream().map(
                todoModel -> new TodoDto(todoModel, getUrl(todoModel))
            ).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getToDoById(@PathVariable("id")UUID id){
        Optional<TodoModel> todoModel = todoService.get(id);
        return ResponseEntity.of(todoModel.map(todo -> new TodoDto(todo, getUrl(todo))));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoDto createTodo(@RequestBody TodoCreate todoCreate) {
        TodoModel todoModel = todoService.create(todoCreate.getTitle());
        return new TodoDto(todoModel, getUrl(todoModel));
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<TodoDto> update(@PathVariable UUID id, @RequestBody TodoUpdate todoUpdate) {
        return ResponseEntity.of(todoService.update(
                id, todoUpdate.getTitle(), todoUpdate.isCompleted(), todoUpdate.getOrder()).map(
                    todo -> new TodoDto(todo, getUrl(todo))
        ));
    }
    @DeleteMapping
    public void deleteAll(@RequestParam(required = false, defaultValue = "false") boolean completed) {
        if(completed){
            todoService.deleteByCompleted(completed);
        }else{
            todoService.deleteAll();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable UUID id) {
        todoService.delete(id);
    }

    //toTodoDto
    public String getUrl(TodoModel todoModel){
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .pathSegment("todos","{id}")
                .buildAndExpand(todoModel.getId()).toUriString();
    }
}