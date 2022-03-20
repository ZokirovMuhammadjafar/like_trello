package jafar.trello.dto.project;

import jafar.trello.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectUpdateDto extends GenericDto {
    private String name;
    private String description;
    private String tz;
    private LocalDate deadline;
    private Long orgId;
}
