package jafar.trello.dto.column;

import jafar.trello.dto.GenericDto;
import org.hibernate.validator.constraints.Length;


public class ColumnUpdateDto extends GenericDto {

    @Length(min = 1)
    private Long id;

    @Length(min = 3)
    private String name;

    @Length(min = 1)
    private int columnOrder;
    @Length(min = 1)
    private Long projectId;
}
