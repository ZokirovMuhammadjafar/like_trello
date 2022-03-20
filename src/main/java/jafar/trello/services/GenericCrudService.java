package jafar.trello.services;


import jafar.trello.dto.BaseDto;
import jafar.trello.dto.GenericDto;
import jafar.trello.entity.BaseEntity;

import java.io.Serializable;

/**
 * @param <E>  -> Entity
 * @param <D>  -> Dto
 * @param <CD> -> Create Dto
 * @param <UD> -> Update Dto
 * @param <K>  -> class that defines the primary key for your pojo class
 */
public interface GenericCrudService<
        E extends BaseEntity,
        D extends GenericDto,
        CD extends BaseDto,
        UD extends GenericDto,
        K extends Serializable> extends GenericService<D, K> {

    K create(CD createDto);

    Void delete(K id);

    Void update(UD updateDto);

}
