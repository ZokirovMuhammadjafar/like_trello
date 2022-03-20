package jafar.trello.dto.task;

import jafar.trello.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bekpulatov Shoxruh, Thu 10:43 AM. 2/24/2022
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskCreateDto implements BaseDto {
    private String name;
    private String description;
    private String level;
    private String priority;
    private Long columnId;
    private Long projectId;
}
