package jafar.trello.dto.auth;

import jafar.trello.dto.GenericDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class AuthUserUpdateDto extends GenericDto {
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phone;
    private MultipartFile photo;
    private String photoPath;
}
