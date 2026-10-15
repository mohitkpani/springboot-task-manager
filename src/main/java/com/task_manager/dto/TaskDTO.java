package com.task_manager.dto;

public record TaskDTO(
		String title,
		String description,
		Boolean completed
		) {

}
