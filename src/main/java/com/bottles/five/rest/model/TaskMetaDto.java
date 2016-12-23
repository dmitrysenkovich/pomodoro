package com.bottles.five.rest.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@ToString
@Getter
@Setter
public class TaskMetaDto {
    private Long taskId;
    private String name;
    private String description;
}
