package jafar.trello.mappers;

import jafar.trello.dto.organization.OrganizationDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import jafar.trello.dto.organization.OrganizationCreateDto;
import jafar.trello.dto.organization.OrganizationUpdateDto;
import jafar.trello.entity.organization.Organization;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface OrganizationMapper extends BaseMapper<Organization, jafar.trello.dto.organization.OrganizationDto, OrganizationCreateDto, OrganizationUpdateDto> {


    @Override
    @Mapping(target = "logo", ignore = true)
    Organization fromCreateDto(OrganizationCreateDto dto);

    @Mapping(target = "logo", ignore = true)
    OrganizationUpdateDto toUpdateDto(Organization organization);

    @Mapping(target = "logo", ignore = true)
    Organization fromUpdateDto(OrganizationUpdateDto dto);

    List<OrganizationDto> toDto(List<Organization> organizations);
}
