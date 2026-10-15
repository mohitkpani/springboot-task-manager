
package com.task_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task_manager.dto.TaskRequestDTO;
import com.task_manager.dto.TaskResponseDTO;
import com.task_manager.entity.Task;
import com.task_manager.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private Task mapToEntity(TaskRequestDTO dto) {
        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setCompleted(
            dto.completed() != null && dto.completed()
        );
        return task;
    }

    private TaskResponseDTO mapToResponseDTO(Task task) {
        return new TaskResponseDTO(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.isCompleted()
        );
    }

    public TaskResponseDTO createTask(TaskRequestDTO taskDTO) {
        Task createdTask = taskRepository.save(mapToEntity(taskDTO));
        return mapToResponseDTO(createdTask);
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(this::mapToResponseDTO)
                .toList();
    }

    public TaskResponseDTO getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return mapToResponseDTO(task);
    }

    public TaskResponseDTO updateTaskPartially(
            Long id, TaskRequestDTO taskDTO) {

        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (taskDTO.title() != null) {
            existingTask.setTitle(taskDTO.title());
        }

        if (taskDTO.description() != null) {
            existingTask.setDescription(taskDTO.description());
        }

        if (taskDTO.completed() != null) {
            existingTask.setCompleted(taskDTO.completed());
        }

        Task updatedTask = taskRepository.save(existingTask);
        return mapToResponseDTO(updatedTask);
    }

    public TaskResponseDTO updateTaskFully(Long id, TaskRequestDTO taskDTO) {

        if (taskDTO.title() == null || taskDTO.completed() == null) {
            throw new IllegalArgumentException(
                "Title and completed status are required for a full update"
            );
        }

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setCompleted(taskDTO.completed());

        Task updatedTask = taskRepository.save(task);
        return mapToResponseDTO(updatedTask);
    }

    public String deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new RuntimeException("Task not found");
        }

        taskRepository.deleteById(id);
        return "Task deleted successfully";
    }
}
