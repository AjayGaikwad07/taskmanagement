package com.taskmanagement.controller;

import com.taskmanagement.entity.Task;
import com.taskmanagement.entity.User;
import com.taskmanagement.service.TaskService;
import com.taskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    // Fetch all tasks for the currently authenticated user
    @GetMapping
    public String getAllUserTasks(Model model) {
        // Get the currently authenticated user
        User currentUser = getCurrentUser();

        // Retrieve tasks only for the logged-in user
        List<Task> tasks = taskService.getTasksByUser(currentUser)
                .stream()
                .map(task -> {
                    task.setFormattedTaskDateTime(task.getTaskDateTime() != null
                            ? task.getTaskDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                            : "N/A");
                    task.setFormattedCompletedDateTime(task.getCompletedDateTime() != null
                            ? task.getCompletedDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
                            : "N/A");
                    return task;
                }).collect(Collectors.toList());

        model.addAttribute("tasks", tasks);
        return "index";  // Updated view name for user-specific task list
    }

//    Fetch a single task by ID.
    @GetMapping("/{id}")
    public String getTaskById(@PathVariable Long id, Model model){
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "edit-task";
    }

//    Add a new task.
    @GetMapping("/new")
    public String createTaskForm(Model model){
        model.addAttribute("task", new Task());
        return "add-task";
    }
    @PostMapping
    public String addTask(@ModelAttribute Task task){
        User currentUser = getCurrentUser();
        task.setUser(currentUser);
        task.setTaskDateTime(LocalDateTime.now());
        taskService.addTask(task);
        return "redirect:/user/tasks";
    }

//    Update a task by ID.
    @PostMapping("/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task){
        User currentUser = getCurrentUser();
        task.setUser(currentUser);
        taskService.updateTask(id, task);
        return "redirect:/user/tasks";
    }

//    Delete a task by ID.
    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return  "redirect:/user/tasks";
    }

    // Helper method to get the currently authenticated user
    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return userService.findByUsername(username);
    }

    
}
