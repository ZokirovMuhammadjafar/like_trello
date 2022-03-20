package jafar.trello.dto.task;

import jafar.trello.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bekpulatov Shoxruh, Thu 10:43 AM. 2/24/2022
 */
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class TaskUpdateDto extends GenericDto {
    private String name;
    private String description;
    private int taskOrder;
    private String level;
    private String priority;
}
