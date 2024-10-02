package com.taskmanagement.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private boolean completed;
    private LocalDateTime taskDateTime;      // Start date and time of the task
    private LocalDateTime completedDateTime;     // Completed date and time of the task

    @ManyToOne(fetch = FetchType.LAZY)       // Link to User entity
    @JoinColumn(name = "user_id",nullable = false)
    private User user;               // The user who owns this task

    // Constructor
    public Task() { }

    public Task(Long id, String name, String description, boolean completed, LocalDateTime taskDateTime, LocalDateTime completedDateTime,User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.taskDateTime = taskDateTime;
        this.completedDateTime = completedDateTime;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public LocalDateTime getTaskDateTime() {
        return taskDateTime;
    }

    public void setTaskDateTime(LocalDateTime taskDateTime) {
        this.taskDateTime = taskDateTime;
    }

    public LocalDateTime getCompletedDateTime() {
        return completedDateTime;
    }

    public void setCompletedDateTime(LocalDateTime completedDateTime) {
        this.completedDateTime = completedDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // New formatted fields
    @Transient
    private String formattedTaskDateTime;

    @Transient
    private String formattedCompletedDateTime;

    // Getters and Setters for the new fields
    public String getFormattedTaskDateTime() { return formattedTaskDateTime; }
    public void setFormattedTaskDateTime(String formattedTaskDateTime) { this.formattedTaskDateTime = formattedTaskDateTime; }

    public String getFormattedCompletedDateTime() { return formattedCompletedDateTime; }
    public void setFormattedCompletedDateTime(String formattedCompletedDateTime) { this.formattedCompletedDateTime = formattedCompletedDateTime; }

}
