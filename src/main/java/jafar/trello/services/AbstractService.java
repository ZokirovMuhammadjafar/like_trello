package jafar.trello.services;


import jafar.trello.mappers.Mapper;
import jafar.trello.validator.Validator;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @param <R> repository
 * @param <M>
 * @param <V>
 */

public abstract class AbstractService<
        R extends JpaRepository,
        M extends Mapper,
        V extends Validator> {
    protected final R repository;
    protected final M mapper;
    protected final V validator;

    protected AbstractService(R repository, M mapper, V validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;

           }
}
