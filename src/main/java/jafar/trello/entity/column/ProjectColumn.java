package jafar.trello.entity.column;


import jafar.trello.entity.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Bekpulatov Shoxruh, Wed 3:09 PM. 2/23/2022
 */
@Getter
@Setter
@Entity
public class ProjectColumn extends Auditable {


    @Column(unique = true, nullable = false)
    private String name;

    @Column(nullable = false)
    private int columnOrder;

    @Column(nullable = false)
    private Long projectId;


}
