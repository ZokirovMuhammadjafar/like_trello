package jafar.trello.mappers;

import jafar.trello.entity.project.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember,Long> {
}
