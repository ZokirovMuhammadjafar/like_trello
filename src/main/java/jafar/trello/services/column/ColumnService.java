package jafar.trello.services.column;


import org.springframework.stereotype.Service;
import jafar.trello.dto.column.ColumnCreateDto;
import jafar.trello.dto.column.ColumnDto;
import jafar.trello.dto.column.ColumnUpdateDto;
import jafar.trello.entity.column.ProjectColumn;
import jafar.trello.mappers.ColumnMapper;
import jafar.trello.repositories.ColumnRepository;
import jafar.trello.services.AbstractService;
import jafar.trello.services.GenericCrudService;
import jafar.trello.services.GenericService;
import jafar.trello.services.task.TaskService;
import jafar.trello.validator.project.ColumnValidator;

import java.util.List;

@Service
public class ColumnService extends AbstractService<
        ColumnRepository,
        ColumnMapper,
        ColumnValidator>
        implements GenericService<ColumnDto, Long>,
        GenericCrudService<ProjectColumn, ColumnDto, ColumnCreateDto, ColumnUpdateDto, Long> {
  private final TaskService service;

    protected ColumnService(ColumnRepository repository,
                            ColumnMapper mapper,
                            ColumnValidator validator,
                            TaskService service) {
        super(repository, mapper, validator);
        this.service = service;
    }

    @Override
    public Long create(ColumnCreateDto createDto) {
        ProjectColumn column = mapper.fromCreateDto(createDto);
        ProjectColumn save = repository.save(column);
        return save.getId();
    }

    @Override
    public Void delete(Long id) {
        repository.delete(id);
       return null;
    }


    @Override
    public Void update(ColumnUpdateDto updateDto) {
        ProjectColumn column = mapper.fromUpdateDto(updateDto);
       repository.save(column);
       return null;
    }

    @Override
    public List<ColumnDto> getAll() {
        List<ProjectColumn> all = repository.findAll();
        return mapper.toDto(all);
    }

    @Override
    public ColumnDto get(Long id) {
        ProjectColumn byId = repository.findById(id).orElseThrow(()->{throw new RuntimeException();});
        return mapper.toDto(byId);
    }

    public List<ColumnDto> getAll(Long id) {
        List<ColumnDto> all = mapper.toDto(repository.findAllByProjectIdAndDeletedFalseOrderByColumnOrder(id));
        all.forEach(column -> {column.setTaskDtos(service.getAll(column.getId()));});
        return all;
    }

    public void deleteAll(Long id) {
         repository.findAllByProjectIdAndDeletedFalseOrderByColumnOrder(id).forEach(projectColumn -> {
             service.deleteAll(projectColumn.getId());
         });
         repository.deleteAllProjectId(id);

    }
}
