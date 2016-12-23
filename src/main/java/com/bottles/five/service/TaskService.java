package com.bottles.five.service;

import com.bottles.five.rest.model.TaskDto;
import com.bottles.five.rest.model.TaskMetaDto;

import java.util.List;

public interface TaskService {
    Long save(TaskDto taskDto);
    Boolean update(TaskDto taskDto);
    TaskDto task(Long id);
    TaskMetaDto taskMeta(Long id);
    List<TaskDto> findByText(String text);
    List<TaskDto> all();
}
