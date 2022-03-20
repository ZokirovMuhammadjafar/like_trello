package jafar.trello.dto.column;

import jafar.trello.dto.GenericDto;
import jafar.trello.dto.task.TaskDto;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ColumnDto extends GenericDto {

    private Long id;
    private String name;
    private int columnOrder;
    private Long projectId;
    private List<TaskDto> taskDtos = new ArrayList<>(0);

}
