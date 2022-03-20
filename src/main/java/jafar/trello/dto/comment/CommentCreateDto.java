package jafar.trello.dto.comment;

import jafar.trello.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Bekpulatov Shoxruh, Thu 11:53 AM. 2/24/2022
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDto implements BaseDto {

    private String text;

    private Long taskId;

}
