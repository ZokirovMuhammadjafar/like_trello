package jafar.trello.entity.project;

import jafar.trello.entity.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

/**
 * @author Bekpulatov Shoxruh, Wed 2:40 PM. 2/23/2022
 */
@Getter
@Setter
@Entity
public class Project extends Auditable {

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String tz;

    @Column(nullable = false)
    private Long orgId;

    @Column(nullable = false)
    private Long teamLeaderId;


    @CreatedDate
    @Column(name = "deadline")
    private LocalDate deadline;


}
