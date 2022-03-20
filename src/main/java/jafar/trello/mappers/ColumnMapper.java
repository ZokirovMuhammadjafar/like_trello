package jafar.trello.mappers;

import org.mapstruct.Mapper;
import jafar.trello.dto.column.ColumnCreateDto;
import jafar.trello.dto.column.ColumnDto;
import jafar.trello.dto.column.ColumnUpdateDto;
import jafar.trello.entity.column.ProjectColumn;


@Mapper(componentModel = "spring")
public interface ColumnMapper extends BaseMapper<ProjectColumn, ColumnDto, ColumnCreateDto, ColumnUpdateDto> {
}
