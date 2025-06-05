package com.example.todoApp.controller;

import com.example.todoApp.domain.TodoItem;
import com.example.todoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/todos")
public class TodoController {
    @Autowired
    TodoService todoService ;


    @GetMapping
    public String helloWorld() {
        return "hello world";
    }

    @GetMapping("/getTodo/{id}")
    public ResponseEntity<Optional<TodoItem>> getTodoById(@PathVariable Long id) {
        return ResponseEntity.ok(todoService.findById(id));
    }

    @GetMapping("/getAllTodos/")
    public ResponseEntity<List<TodoItem>> GetAllTodo() {
        List<TodoItem> listOfTodos = todoService.findAll();
        return ResponseEntity.ok(listOfTodos);
    }

    @PostMapping("/createTodo/")
    public ResponseEntity<TodoItem> createTodo(@RequestParam String description,
                                               @RequestParam LocalDateTime dateTime) {
        TodoItem todo = todoService.createTodo(description, dateTime);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/deleteTodo/{id}")
    public ResponseEntity<Void> deleteTodo(Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateTodo/{id}")
    public ResponseEntity<TodoItem> updateTodo(@PathVariable Long id,
                                               @RequestParam String description,
                                               @RequestParam LocalDateTime dateTime) {
        TodoItem updatedTodo = todoService.updateTodo(id, description, dateTime);
        return ResponseEntity.ok(updatedTodo);
    }

    @PutMapping("/updateDescription/{id}")
    public ResponseEntity<TodoItem> updateDescription(@PathVariable Long id,
                                                      @RequestParam String description) {
        TodoItem updatedTodo = todoService.updateDescription(id, description);
        return ResponseEntity.ok(updatedTodo);
    }

    @PutMapping("/updateDate/{id}")
    public ResponseEntity<TodoItem> updateDate(@PathVariable Long id,
                                               @RequestParam LocalDateTime dateTime) {
        TodoItem updatedTodo = todoService.updateDueAt(id, dateTime);
        return ResponseEntity.ok(updatedTodo);
    }

}
