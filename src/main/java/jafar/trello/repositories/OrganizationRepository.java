package jafar.trello.repositories;

import jafar.trello.entity.organization.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    Organization findOrganizationByIdAndDeletedFalse(Long id);

    List<Organization> findAllByDeletedFalseOrderByIdAsc();


    @Transactional
    @Modifying
    @Query(value = "Update Organization o SET o.deleted = true WHERE o.id=:id")
    void deleteOrganization(Long id);


//    @Transactional
//    @Modifying
//    @Query(value = "Update Organization o SET o.name =:dto. WHERE o.id=:id")
//    void updateOrganization(OrganizationUpdateDto dto);
}
