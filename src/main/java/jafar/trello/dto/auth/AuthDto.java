package jafar.trello.dto.auth;



import jafar.trello.dto.GenericDto;
import jafar.trello.entity.auth.AuthRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthDto extends GenericDto {
    private Long orgId;
    private String firstName;
    private String lastName;
    private AuthRole role;
    private String username;
    private String picturePath;
    private String phone;
    private String email;
}
