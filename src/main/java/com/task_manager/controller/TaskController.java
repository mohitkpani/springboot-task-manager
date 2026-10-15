package com.task_manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task_manager.dto.TaskDTO;
import com.task_manager.service.TaskService;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
	 
	private final TaskService taskService;
	
	public TaskController(TaskService taskService) {
		super();
		this.taskService = taskService;
	}


	@PostMapping()
	public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO dto){
		TaskDTO response = taskService.createTask(dto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<TaskDTO>> getAlltasks(){
		List<TaskDTO> response = taskService.getAllTasks();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> getTaskById(@PathVariable Long id){
		TaskDTO response = taskService.getTaskById(id);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTaskPartially(
							@PathVariable Long id, 
							@RequestBody TaskDTO taskDTO){
		TaskDTO response = taskService.updateTaskPartially(id, taskDTO);
		return new ResponseEntity<>(response, HttpStatus.OK); 
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTaskFully(
							@PathVariable Long id,
							@RequestBody TaskDTO taskDTO){
		TaskDTO response = taskService.updateTaskFully(id, taskDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTask(@PathVariable Long id){
		String response = taskService.deleteTask(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
