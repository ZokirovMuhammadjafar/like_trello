package jafar.trello.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import jafar.trello.dto.task.TaskCreateDto;
import jafar.trello.dto.task.TaskDto;
import jafar.trello.dto.task.TaskUpdateDto;
import jafar.trello.entity.task.Task;

@Component
@Mapper(componentModel = "spring")
public interface TaskMapper extends BaseMapper<Task, TaskDto, TaskCreateDto, TaskUpdateDto> {

    TaskUpdateDto toUpdateDto(TaskDto taskDto);


}
