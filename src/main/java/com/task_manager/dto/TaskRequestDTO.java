package com.task_manager.dto;

public record TaskRequestDTO(
		String title,
		String description,
		Boolean completed) {

}
