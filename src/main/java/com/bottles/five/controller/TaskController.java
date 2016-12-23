package com.bottles.five.controller;

import com.bottles.five.rest.model.TaskDto;
import com.bottles.five.rest.model.TaskMetaDto;
import com.bottles.five.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    private TaskService taskService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<TaskDto>> tasks(@RequestParam(value = "taskId", required = false) Long taskId,
                                              @RequestParam(value = "text", required = false) String text) {
        LOGGER.info("Tasks GET request");

        LOGGER.info("Task id: " + taskId);
        LOGGER.info("Text: " + text);

        List<TaskDto> response = null;
        if (taskId != null) {
            TaskDto taskDto = taskService.task(taskId);
            response = Arrays.asList(taskDto);
        }
        else if (text != null) {
            response = taskService.findByText(text);
        }
        else {
            response = taskService.all();
        }

        if (response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/meta", method = RequestMethod.GET)
    public ResponseEntity<TaskMetaDto> meta(@RequestParam("taskId") Long taskId) {
        LOGGER.info("Task meta GET request");

        LOGGER.info("Task id: " + taskId);

        TaskMetaDto taskMetaDto = taskService.taskMeta(taskId);
        if (taskMetaDto == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(taskMetaDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<Long> save(@RequestBody TaskDto taskDto) {
        LOGGER.info("Save POST request");

        LOGGER.info("Task: " + taskDto);

        Long taskId = taskService.save(taskDto);

        return new ResponseEntity<>(taskId, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Boolean> update(@RequestBody TaskDto taskDto) {
        LOGGER.info("Update POST request");

        LOGGER.info("Task: " + taskDto);

        Boolean updated = taskService.update(taskDto);
        if (!updated)
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
