package jafar.trello.entity.action;

import jafar.trello.entity.Auditable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;

/**
 * @author Bekpulatov Shoxruh, Thu 10:58 PM. 2/24/2022
 */
@Entity
@Setter
@Getter
public class Action extends Auditable {

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private String authorUsername;

    @Column(nullable = false)
    private Long taskId;

    @CreatedDate
    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "TIMESTAMP default NOW()")
    private LocalDateTime createdAt;

}
