package jafar.trello.mappers;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import jafar.trello.dto.comment.CommentCreateDto;
import jafar.trello.dto.comment.CommentDto;
import jafar.trello.dto.comment.CommentUpdateDto;
import jafar.trello.entity.comment.Comment;

@Component
@Mapper(componentModel = "spring")
public interface CommentMapper extends BaseMapper<Comment, CommentDto, CommentCreateDto, CommentUpdateDto> {
    CommentUpdateDto toUpdateDto(CommentDto commentDto);
}
