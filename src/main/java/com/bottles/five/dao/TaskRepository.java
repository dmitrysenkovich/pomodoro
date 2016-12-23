package com.bottles.five.dao;

import com.bottles.five.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(String name, String description);
}
