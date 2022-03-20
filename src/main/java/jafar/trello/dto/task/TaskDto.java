package jafar.trello.dto.task;

import jafar.trello.dto.GenericDto;
import jafar.trello.entity.task.Task_Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDto extends GenericDto {
    private String name;
    private String description;
    private int taskOrder;
    private String level;
    private String priority;
    private Long columnId;
    private String createdAt;
    private String updatedAt;
    private List<Task_Member> taskMembers = new ArrayList<>();
}
