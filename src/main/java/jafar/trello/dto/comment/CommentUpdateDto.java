package jafar.trello.dto.comment;

import jafar.trello.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bekpulatov Shoxruh, Thu 11:54 AM. 2/24/2022
 */
@Getter
@Setter
@AllArgsConstructor
public class CommentUpdateDto extends GenericDto {
    private String text;
}
