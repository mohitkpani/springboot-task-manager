package com.task_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task_manager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
