package com.task_manager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.task_manager.dto.TaskDTO;
import com.task_manager.entity.Task;
import com.task_manager.repository.TaskRepository;

@Service
public class TaskService {
	
	private final TaskRepository taskRepository;

	public TaskService(TaskRepository taskRepository) {
		super();
		this.taskRepository = taskRepository;
	}
	
	private Task mapToEntity(TaskDTO taskDTO) {
		Task task = new Task();
		task.setTitle(taskDTO.title());
		task.setDescription(taskDTO.description());
		task.setCompleted(taskDTO.completed());
		return task;
	}
	
	private TaskDTO mapToResponseDTO(Task task) {
		return new TaskDTO(
				task.getTitle(),
				task.getDescription(),
				task.isCompleted()
				);
	}
	
	public TaskDTO createTask(TaskDTO taskDTO) {
		Task createdTask = taskRepository.save(mapToEntity(taskDTO));
		return mapToResponseDTO(createdTask);
	}
	
	public List<TaskDTO> getAllTasks() {
		List<Task> taskList = taskRepository.findAll();
		return taskList.stream().map(this::mapToResponseDTO).toList();
	}
	
	public TaskDTO getTaskById(Long id) {
		Task task = taskRepository.findById(id).
		orElseThrow(() -> new RuntimeException("Task not found"));
		return mapToResponseDTO(task);
	}
	
	public TaskDTO updateTaskPartially(Long id, TaskDTO taskDTO) {
		Task existingTask = taskRepository.findById(id).
				orElseThrow(() -> new RuntimeException("Task not found"));
		
		if(taskDTO.title() != null) {
			existingTask.setTitle(taskDTO.title());	
		}
		if(taskDTO.description() != null) {
			existingTask.setDescription(taskDTO.description());	
		}
		if(taskDTO.completed() != null) {
			existingTask.setCompleted(taskDTO.completed());	
		}
		
		Task updatedTask = taskRepository.save(existingTask);
		return mapToResponseDTO(updatedTask);
		
	}
	
	public TaskDTO updateTaskFully(Long id, TaskDTO taskDTO) {
		Task task = taskRepository.findById(id).
		orElseThrow(() -> new RuntimeException("Task not found"));
		
		task.setTitle(taskDTO.title());
		task.setDescription(taskDTO.description());
		task.setCompleted(taskDTO.completed());
		
		Task updatedTask = taskRepository.save(task);
		return mapToResponseDTO(updatedTask);
	}
	
	public String deleteTask(Long id) {
		if(!taskRepository.existsById(id)) {
			throw new RuntimeException("Task not found");
		}
		taskRepository.deleteById(id);
		return "Task deleted successfully";
	}

}
