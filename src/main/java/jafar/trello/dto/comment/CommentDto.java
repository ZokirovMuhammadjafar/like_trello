package jafar.trello.dto.comment;

import jafar.trello.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentDto extends GenericDto {

    private String text;

    private String authorUsername;

    private Long taskId;

    private LocalDateTime createdAt;


}
