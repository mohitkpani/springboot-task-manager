
package com.task_manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_manager.dto.TaskRequestDTO;
import com.task_manager.dto.TaskResponseDTO;
import com.task_manager.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = { "http://localhost:5173",
        "http://192.168.31.222:5173",
        "https://springboot-task-manager.vercel.app" })
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(
            @RequestBody TaskRequestDTO dto) {

        TaskResponseDTO response = taskService.createTask(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {

        List<TaskResponseDTO> response = taskService.getAllTasks();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(
            @PathVariable Long id) {

        TaskResponseDTO response = taskService.getTaskById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTaskPartially(
            @PathVariable Long id,
            @RequestBody TaskRequestDTO taskDTO) {

        TaskResponseDTO response = taskService.updateTaskPartially(id, taskDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTaskFully(
            @PathVariable Long id,
            @RequestBody TaskRequestDTO taskDTO) {

        TaskResponseDTO response = taskService.updateTaskFully(id, taskDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(
            @PathVariable Long id) {

        String response = taskService.deleteTask(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
