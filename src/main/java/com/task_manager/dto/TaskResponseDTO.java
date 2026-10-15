
package com.task_manager.dto;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        Boolean completed
) {
}
