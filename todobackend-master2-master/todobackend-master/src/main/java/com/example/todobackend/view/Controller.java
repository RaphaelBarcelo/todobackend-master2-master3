package com.example.todobackend.view;

import com.example.todobackend.view.TodoCreate;
import com.example.todobackend.view.TodoUpdate;
import com.example.todobackend.model.TodoModel;
import com.example.todobackend.data.TodoRepository;
import com.example.todobackend.service.TodoServiceImp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping
public class Controller {
    private final TodoServiceImp todoServiceImp;

    public Controller(TodoRepository todoRepository) {
        this.todoServiceImp = new TodoServiceImp(todoRepository);
    }

    @GetMapping //(method = RequestMethod.GET, value = "/")
    public List<TodoModel> getTodos(){
        return todoServiceImp.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoModel> getToDoById(@PathVariable("id")UUID id){
        return ResponseEntity.of(todoServiceImp.get(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TodoModel createTodo(@RequestBody TodoCreate todoCreate) {
        return todoServiceImp.create(todoCreate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoModel> update(@PathVariable int id, @RequestBody TodoUpdate todoUpdate) {
        return ResponseEntity.of(todoServiceImp.update(id, todoUpdate));
    }
    @DeleteMapping
    public void deleteAll(@RequestParam(required = false, defaultValue = "false") boolean completed) {
        if(completed){
            todoServiceImp.deleteByCompleted(completed);
        }else{
            todoServiceImp.deleteAll();
        }
    }

    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable int id) {
        todoServiceImp.delete(id);
    }

    //toTodoDto
    public String getUrl(TodoModel todoModel){
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .pathSegment("todos","{id}")
                .buildAndExpand(todoModel.getId()).toUriString();
    }

    /*@RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public @ResponseBody TodoItem getTodoById(@PathVariable int id) {
        return todoServiceImp.get(id);
    }*/


}