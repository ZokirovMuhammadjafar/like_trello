package jafar.trello.repositories;

import jafar.trello.entity.auth.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AuthRoleRepository extends JpaRepository<AuthRole,Long> {
    Optional<AuthRole> findAuthRoleByCode(String code);

}
