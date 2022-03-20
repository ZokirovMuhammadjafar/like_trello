package jafar.trello.entity.auth;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Entity
@Getter
@Setter
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @Column(name = "private_token", nullable = false)
    private String privateToken;


    private LocalDateTime expire=LocalDateTime.of(LocalDate.now(),LocalTime.ofSecondOfDay(LocalTime.now().getSecond()+300));

}