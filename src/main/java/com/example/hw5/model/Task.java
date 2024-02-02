package com.example.hw5.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @Column(nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime dateCreated;

    public Task() {
        //this.status = TaskStatus.NOT_STARTED;
        this.dateCreated = LocalDateTime.now();
    }

}
