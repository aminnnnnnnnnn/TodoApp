package com.example.todoApp.service;

import com.example.todoApp.domain.TodoItem;
import com.example.todoApp.repository.TodoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.NonNull;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    TodoRepository todoRepository;  

    public List<TodoItem> findAll(){
        return todoRepository.findAll();
    }

    public Optional<TodoItem> findById(Long id){
        return todoRepository.findById(id);
    }

    public void deleteTodo(Long id){
        if (todoRepository.existsById(id)) {
            todoRepository.deleteById(id);
        }
    }

    public TodoItem createTodo(@NonNull String description, LocalDateTime dueAtTime){
        TodoItem todo = new TodoItem();
        todo.setDescription(description);
        todo.setDueAt(dueAtTime);
        if (todo.getDueAt().isBefore(todo.getCreatedAt()) || todo.getDueAt().equals(todo.getCreatedAt())){
            throw new RuntimeException("the dueAt must be after the the Creation Time");
        }
            return todo;
    }

    public TodoItem updateDescription(Long id,@NotNull String description){
        if (id == null || description == null) {
            throw new IllegalArgumentException("ID and description cannot be null");
        }
        TodoItem todo = findTodo(id);
        todo.setDescription(description);
        return todoRepository.save(todo);
    }
    public TodoItem updateDueAt(Long id, LocalDateTime dateTime){
        if (id == null || dateTime == null) {
            throw new IllegalArgumentException("ID and dateTime cannot be null");
        }
        TodoItem todo = findTodo(id);
        todo.setDueAt(dateTime);
        return todoRepository.save(todo);
    }
    public TodoItem updateTodo(Long id, @NotNull String description, LocalDateTime dateTime){
        if (id == null || description == null || dateTime == null) {
            throw new IllegalArgumentException("ID, description, and dateTime cannot be null");
        }
        TodoItem todo = findTodo(id);
        todo.setDueAt(dateTime);
        todo.setDescription(description);
        return todoRepository.save(todo);
    }

    private TodoItem findTodo(Long id){
        return todoRepository.findById(id).orElseThrow((()-> new EntityNotFoundException("Todo with id " + id + " not found")));
    }

}
