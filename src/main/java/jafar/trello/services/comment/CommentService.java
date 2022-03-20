package jafar.trello.services.comment;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import jafar.trello.configs.security.UserDetails;
import jafar.trello.dto.comment.CommentCreateDto;
import jafar.trello.dto.comment.CommentDto;
import jafar.trello.dto.comment.CommentUpdateDto;
import jafar.trello.entity.comment.Comment;
import jafar.trello.enums.ActionTexts;
import jafar.trello.mappers.CommentMapper;
import jafar.trello.repositories.CommentRepository;
import jafar.trello.repositories.TaskRepository;
import jafar.trello.services.AbstractService;
import jafar.trello.services.GenericCrudService;
import jafar.trello.validator.Validator;
import jafar.trello.validator.task.CommentValidator;

import java.util.List;

@Service
public class CommentService extends AbstractService<CommentRepository, CommentMapper, Validator>
        implements GenericCrudService<Comment, CommentDto, CommentCreateDto, CommentUpdateDto, Long> {

    private final TaskRepository taskRepository;

    protected CommentService(CommentRepository repository,
                             CommentMapper mapper,
                             CommentValidator validator, TaskRepository taskRepository) {
        super(repository, mapper, validator);

        this.taskRepository = taskRepository;
    }


    @Override
    public List<CommentDto> getAll() {
        return mapper.toDto(repository.findAllByDeletedFalse());
    }

    public List<CommentDto> getAll(Long taskId) {
        return mapper.toDto(repository.findAllByTaskIdd(taskId));
    }

    @Override
    public Long create(CommentCreateDto dto) {
        Comment comment = mapper.fromCreateDto(dto);
        comment.setAuthorUsername(((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername());
        taskRepository.addAction(dto.getTaskId(), comment.getAuthorUsername(), ActionTexts.NEW_COMMIT.getText());
        return repository.save(comment).getId();
    }

    @Override
    public CommentDto get(Long id) {
        return mapper.toDto(repository.findByIdAndDeletedFalse(id));
    }

    @Override
    public Void delete(Long id) {
        repository.delete(id);
        return null;
    }

    public CommentUpdateDto getUpdateDto(Long id) {
        return mapper.toUpdateDto(this.get(id));
    }

    @Override
    public Void update(CommentUpdateDto dto) {
        repository.update(dto.getId(), dto.getText());
        return null;
    }

    public int getCommentCount(String name) {
        return repository.getCommentCount(name).size();
    }
}
