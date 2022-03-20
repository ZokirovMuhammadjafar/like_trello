package jafar.trello.mappers;

import jafar.trello.dto.BaseDto;
import jafar.trello.dto.GenericDto;
import jafar.trello.entity.BaseEntity;

import java.util.List;

/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 */

public interface BaseMapper<
        E extends BaseEntity,
        D extends GenericDto,
        CD extends BaseDto,
        UD extends GenericDto> extends Mapper {

    D toDto(E e);

    List<D> toDto(List<E> e);

    E fromCreateDto(CD cd);

    E fromUpdateDto(UD ud);

}
