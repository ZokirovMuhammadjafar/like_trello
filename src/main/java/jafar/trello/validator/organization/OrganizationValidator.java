package jafar.trello.validator.organization;

import jafar.trello.dto.organization.OrganizationCreateDto;
import jafar.trello.dto.organization.OrganizationUpdateDto;
import jafar.trello.exceptions.NotFoundException;
import jafar.trello.validator.AbstractValidator;
import org.springframework.stereotype.Component;



// TODO: 2/24/2022  o'zomizni exception yozishimiz kerak

@Component
public class OrganizationValidator extends AbstractValidator<OrganizationCreateDto, OrganizationUpdateDto, Long> {


    @Override
    public void validateKey(Long id) throws NotFoundException {

    }

    @Override
    public void validOnCreate(OrganizationCreateDto organizationCreateDto) throws NotFoundException {

    }

    @Override
    public void validOnUpdate(OrganizationUpdateDto cd) throws NotFoundException {

    }

}
