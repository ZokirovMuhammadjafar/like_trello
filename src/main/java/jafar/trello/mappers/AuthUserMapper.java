package jafar.trello.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import jafar.trello.dto.auth.AddAdminDto;
import jafar.trello.dto.auth.AuthDto;
import jafar.trello.dto.auth.AuthUserCreateDto;
import jafar.trello.dto.auth.AuthUserUpdateDto;
import jafar.trello.entity.auth.AuthUser;

@Component
@Mapper(componentModel = "spring")
public interface AuthUserMapper extends BaseMapper<AuthUser,AuthDto, AuthUserCreateDto, AuthUserUpdateDto> {

    AuthUser fromDto(AddAdminDto dto);

}
