package jafar.trello.entity.auth;


import jafar.trello.entity.Auditable;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser extends Auditable {

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "role_id", nullable = false)
    private AuthRole role;

    private String firstName;


    private String phone;

    private String picturePath;

    private String lastName;


    private String email;


    private Long organizationId;


    private boolean active ;

    private boolean blocked;


}
