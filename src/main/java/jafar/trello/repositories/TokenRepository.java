package jafar.trello.repositories;

import jafar.trello.entity.auth.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByPrivateToken(String token);
}
