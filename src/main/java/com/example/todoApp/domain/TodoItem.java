package com.example.todoApp.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Entity
@Table
public class TodoItem {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Getter
    @Setter
    private String description;
    @Getter
    private LocalDateTime createdAt = LocalDateTime.now();
    @Getter
    @Setter
    private LocalDateTime dueAt;
    @Getter
    @Setter
    private boolean isDone;



}
