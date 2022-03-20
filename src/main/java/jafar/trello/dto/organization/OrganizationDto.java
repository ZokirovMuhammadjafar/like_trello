package jafar.trello.dto.organization;


import jafar.trello.dto.GenericDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationDto extends GenericDto {

    private Long id;

    private String name;

    private String logo;

    private String email;

    private String phone;

    private String address;

    private Long registrationNumber;

    private String webSite;

    private boolean blocked;
}
