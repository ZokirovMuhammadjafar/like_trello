package jafar.trello.dto.project;

import jafar.trello.dto.GenericDto;
import jafar.trello.dto.column.ColumnDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectDto extends GenericDto {
    private String name;
    private String description;
    private String tz;
    private String organizationName;
    private String createdAt;
    private LocalDate deadline;
    private List<ColumnDto> columns = new ArrayList<>();
    private int projectMembersCount = 0;


    public Integer getTasksCount() {
        int countOfTasks = 0;


        for (ColumnDto column : columns) {
            countOfTasks += column.getTaskDtos().size();
        }

        return countOfTasks;

    }

    public Integer getDoneTasksCount() {
        int countOfTasks = 0;


        for (ColumnDto column : columns) {
            if (column.getName().equals("Done"))
                countOfTasks += column.getTaskDtos().size();
        }

        return countOfTasks;
    }

    public Integer getFrozenTasksCount() {
        int countOfTasks = 0;


        for (ColumnDto column : columns) {
            if (column.getName().equals("Frozen"))
                countOfTasks += column.getTaskDtos().size();
        }

        return countOfTasks;
    }
}
