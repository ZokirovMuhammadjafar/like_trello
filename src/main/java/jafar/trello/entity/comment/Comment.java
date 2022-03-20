package jafar.trello.entity.comment;

import jafar.trello.entity.Auditable;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author Bekpulatov Shoxruh, Wed 3:16 PM. 2/23/2022
 */

@Getter
@Setter
@Entity
public class Comment extends Auditable {



    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String authorUsername;

    @Column(nullable = false)
    private Long taskId;
}
