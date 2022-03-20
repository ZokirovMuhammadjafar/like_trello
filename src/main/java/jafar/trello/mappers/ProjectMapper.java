package jafar.trello.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import jafar.trello.dto.project.ProjectCreateDto;
import jafar.trello.dto.project.ProjectDto;
import jafar.trello.dto.project.ProjectUpdateDto;
import jafar.trello.entity.project.Project;


@Component
@Mapper(componentModel = "spring")
public interface ProjectMapper extends BaseMapper<Project, ProjectDto, ProjectCreateDto, ProjectUpdateDto> {
    ProjectUpdateDto toUpdateDto(ProjectDto dto);

    @Override
    @Mapping(target = "tz",ignore = true)
    Project fromCreateDto(ProjectCreateDto dto);
}
