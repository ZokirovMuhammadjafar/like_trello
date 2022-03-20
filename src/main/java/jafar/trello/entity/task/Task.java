package jafar.trello.entity.task;

import jafar.trello.entity.Auditable;
import jafar.trello.enums.Level;
import jafar.trello.enums.Priority;
import lombok.Getter;
import lombok.Setter;


import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Bekpulatov Shoxruh, Wed 2:40 PM. 2/23/2022
 */
@Getter
@Setter
@Entity(name = "Tasks")
public class Task extends Auditable {

    @Column(unique = true, nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private int taskOrder;

    @Column(nullable = false)
    private String level = Level.EASY.name();

    @Column(nullable = false)
    private String priority = Priority.EASY.name();

    @Column(nullable = false)
    private Long columnId;

}
