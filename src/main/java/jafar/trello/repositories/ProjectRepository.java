package jafar.trello.repositories;

import jafar.trello.dto.project.ProjectUpdateDto;
import jafar.trello.entity.auth.AuthUser;
import jafar.trello.entity.project.Project;
import jafar.trello.entity.project.ProjectMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import javax.transaction.Transactional;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findByIdAndDeletedFalse(Long id);

    List<Project> findAllByOrgIdAndDeletedFalse(Long id);

    List<Project> findAllByDeletedFalse();

    List<Project> findProjectByOrgIdAndDeletedFalseOrderByIdAsc(Long id);

    @Transactional
    @Modifying
    @Query("update Project p set p.deleted = true where p.id = :id")
    void delete(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value = "update Project p set p.name = #{#dto.name}, p.description = #{#dto.description}, p.tz = #{#dto.tz}, p.orgId = #{#dto.orgId}  where p.id = #{#dto.id}", nativeQuery = true)
    void update(@Param("dto") ProjectUpdateDto dto);

    @Query("select u.teamLeaderId from  Project u where u.id=:id")
    Long getTeamLead(@Param("id") Long projectId);


    @Query("from AuthUser au where au.organizationId = (select p.orgId from Project p where p.id = :id)")
    List<AuthUser> getProjectMembersFromOrganization(Long id);

    @Query("from AuthUser au where au.id = any (select pm.userId from ProjectMember pm where pm.projectId = :id)")
    List<AuthUser> getProjectMembersFromProject(Long id);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO project_member (user_id, project_id) VALUES(?2, ?1) ", nativeQuery = true)
    void addMember(Long projectId, Long memberId);

    @Query("from ProjectMember p where p.userId=:id")
    List<ProjectMember> getProjectCount(Long id);


    @Query(value = "select count(p.id) from ProjectMember p where p.projectId=:id ")
    int getCount(Long id);

    @Transactional
    @Modifying
    @Query("update Tasks t set t.columnId = ?1 where t.id = ?2")
    void updateTaskColumn(Long columnId, Long taskId);
}

