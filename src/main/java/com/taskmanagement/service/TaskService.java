package com.taskmanagement.service;

import com.taskmanagement.entity.Task;
import com.taskmanagement.entity.User;
import com.taskmanagement.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    //Fetch all tasks.
    public List<Task> getTasksByUser(User user){
        return taskRepository.findByUser(user);
    }

    //    Fetch a single task by ID.
    public Task getTaskById(Long id){
        return taskRepository.findById(id).orElseThrow(()->new RuntimeException("Task not found"));
    }

    //    Add a new task.
    public Task addTask(Task task){
       if (task.getTaskDateTime() == null){
           task.setTaskDateTime(LocalDateTime.now());
       }
       return taskRepository.save(task);
    }

    //    Update a task by ID.
    public Task updateTask(Long id, Task taskDetails){
        Task task =getTaskById(id);
        task.setName(taskDetails.getName());
        task.setDescription(taskDetails.getDescription());
        task.setCompleted(taskDetails.isCompleted());
        task.setTaskDateTime(taskDetails.getTaskDateTime());
        if (taskDetails.isCompleted()){
            task.setCompletedDateTime(LocalDateTime.now());
        } else {
            task.setCompletedDateTime(null);
        }
        return taskRepository.save(task);
    }

    //    Delete a task by ID.
    public void deleteTask(Long id){
        taskRepository.deleteById(id);
    }

}
