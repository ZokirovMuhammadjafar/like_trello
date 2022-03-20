package jafar.trello.validator.auth;

import jafar.trello.dto.auth.AuthUserCreateDto;
import jafar.trello.dto.auth.AuthUserUpdateDto;
import jafar.trello.exceptions.NotFoundException;
import jafar.trello.validator.AbstractValidator;
import jafar.trello.validator.Validator;
import org.springframework.stereotype.Component;


@Component
public class AuthUserValidator extends AbstractValidator<AuthUserCreateDto, AuthUserUpdateDto,Long> implements Validator {

    @Override
    public void validateKey(Long id) throws NotFoundException {

    }

    @Override
    public void validOnCreate(AuthUserCreateDto authUserCreateDto) throws NotFoundException {

    }

    @Override
    public void validOnUpdate(AuthUserUpdateDto cd) throws NotFoundException {

    }
}
