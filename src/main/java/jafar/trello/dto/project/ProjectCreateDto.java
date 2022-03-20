package jafar.trello.dto.project;

import jafar.trello.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProjectCreateDto implements BaseDto {
    private String name;
    private String description;
    private MultipartFile tz;
    private String deadline;
}
