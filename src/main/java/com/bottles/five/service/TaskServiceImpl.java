package com.bottles.five.service;

import com.bottles.five.dao.TaskRepository;
import com.bottles.five.model.Task;
import com.bottles.five.rest.model.TaskDto;
import com.bottles.five.rest.model.TaskMetaDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Long save(TaskDto taskDto) {
        Task task = new Task();
        BeanUtils.copyProperties(taskDto, task);
        task = taskRepository.save(task);
        taskRepository.flush();
        return task.getTaskId();
    }

    @Override
    public Boolean update(TaskDto taskDto) {
        Task task = taskRepository.findOne(taskDto.getTaskId());
        if (task == null)
            return false;

        BeanUtils.copyProperties(taskDto, task);
        taskRepository.save(task);
        return true;
    }

    @Override
    public TaskDto task(Long id) {
        Task task = taskRepository.findOne(id);
        if (task == null)
            return null;

        TaskDto taskDto = new TaskDto();
        BeanUtils.copyProperties(task, taskDto);
        return taskDto;
    }

    @Override
    public TaskMetaDto taskMeta(Long id) {
        Task task = taskRepository.findOne(id);
        if (task == null)
            return null;

        TaskMetaDto taskMetaDto = new TaskMetaDto();
        BeanUtils.copyProperties(task, taskMetaDto);
        return taskMetaDto;
    }

    @Override
    public List<TaskDto> findByText(String text) {
        List<Task> tasks = taskRepository.findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(text, text);
        List<TaskDto> taskDtos = new LinkedList<>();
        for (Task task : tasks) {
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(task, taskDto);
            taskDtos.add(taskDto);
        }
        return taskDtos;
    }

    @Override
    public List<TaskDto> all() {
        List<Task> tasks = taskRepository.findAll();
        List<TaskDto> taskDtos = new LinkedList<>();
        for (Task task : tasks) {
            TaskDto taskDto = new TaskDto();
            BeanUtils.copyProperties(task, taskDto);
            taskDtos.add(taskDto);
        }
        return taskDtos;
    }
}
