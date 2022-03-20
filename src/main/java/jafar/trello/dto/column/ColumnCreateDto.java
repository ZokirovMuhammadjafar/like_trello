package jafar.trello.dto.column;

import jafar.trello.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
public class ColumnCreateDto implements BaseDto {

    @Length(min = 3)
    private String name;
    @Length(min = 1)
    private Long projectId;
}
