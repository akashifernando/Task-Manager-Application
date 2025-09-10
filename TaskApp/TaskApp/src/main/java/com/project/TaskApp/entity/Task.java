package com.project.TaskApp.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be empty")
    private String title;

    @Column(length = 1000)
    private String description;

    @NotNull
    private Boolean completed;

    private String subject;
    private LocalDate dueDate;
//timestamps
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)//foreign key
    @JsonIgnore//Prevent the user data itteraative
    private AppUser user;


}
