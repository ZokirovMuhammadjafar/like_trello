package jafar.trello.repositories;

import jafar.trello.entity.action.Uploads;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UploadsRepository extends JpaRepository<Uploads,Long> {
    Optional<Uploads>findByGeneratedName(String filename);
}
