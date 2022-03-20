package jafar.trello.services.project;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jafar.trello.configs.security.UserDetails;
import jafar.trello.dto.project.ProjectCreateDto;
import jafar.trello.dto.project.ProjectDto;
import jafar.trello.dto.project.ProjectUpdateDto;
import jafar.trello.entity.auth.AuthUser;
import jafar.trello.entity.project.Project;
import jafar.trello.mappers.ProjectMapper;
import jafar.trello.repositories.ProjectRepository;
import jafar.trello.services.AbstractService;
import jafar.trello.services.GenericCrudService;
import jafar.trello.services.column.ColumnService;
import jafar.trello.services.file.FileStorageService;
import jafar.trello.utils.FileUploadUtils;
import jafar.trello.validator.project.ProjectValidator;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService extends AbstractService<ProjectRepository, ProjectMapper, ProjectValidator>
        implements GenericCrudService<Project, ProjectDto, ProjectCreateDto, ProjectUpdateDto, Long> {


    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private final ColumnService columnService;
    private final FileStorageService fileStorageService;

    protected ProjectService(ProjectRepository repository, ProjectMapper mapper, ProjectValidator validator,
                             ColumnService columnService, FileStorageService fileStorageService) {
        super(repository, mapper, validator);
        this.columnService = columnService;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public Long create(ProjectCreateDto createDto) {
        MultipartFile file = createDto.getTz();
        Project project = mapper.fromCreateDto(createDto);
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        project.setOrgId(principal.getOrganization());
        project.setTeamLeaderId(principal.getId());
        project.setTz(FileUploadUtils.UPLOAD_DIRECTORY + fileStorageService.store(file));
        return repository.save(project).getId();
    }

    @Override
    public Void delete(Long id) {
        columnService.deleteAll(id);
        repository.delete(id);
        return null;
    }

    @Override
    public Void update(ProjectUpdateDto updateDto) {
        repository.update(updateDto);
        return null;
    }

    @Override
    public List<ProjectDto> getAll() {
        return repository.findAllByDeletedFalse().stream().map(project -> {
            ProjectDto dto = mapper.toDto(project);
            dto.setCreatedAt(project.getCreatedAt().format(FORMATTER));
            return dto;
        }).collect(Collectors.toList());
    }

    public List<ProjectDto> getAll(Long id) {
//        validator.


        return repository.findAllByOrgIdAndDeletedFalse(id).stream().map(project -> {
            ProjectDto dto = mapper.toDto(project);
            dto.setCreatedAt(project.getCreatedAt().format(FORMATTER));
            dto.setProjectMembersCount(repository.getCount(dto.getId()));
            return dto;

        }).collect(Collectors.toList());
    }

    @Override
    public ProjectDto get(Long id) {
        ProjectDto projectDto = mapper.toDto(repository.findByIdAndDeletedFalse(id));
        projectDto.setColumns(columnService.getAll(projectDto.getId()));
        return projectDto;
    }

    public ProjectUpdateDto getUpdateDto(Long id) {
        return mapper.toUpdateDto(get(id));
    }

    public Long getTeamLead(Long projectId) {
        return repository.getTeamLead(projectId);
    }

    public List<AuthUser> getMembers(Long id) {
        return repository.getProjectMembersFromProject(id);
    }

    public List<AuthUser> getMembersFromOrganization(Long id) {
        List<AuthUser> projectMembers;
        projectMembers = repository.getProjectMembersFromOrganization(id);

        return projectMembers;
    }

    public void addMember(Long projectId, Long memberId) {
        repository.addMember(projectId, memberId);
    }

    public int getProjectCount(Long id) {
       return repository.getProjectCount(id).size();
    }

    public void updateTaskColumn(Long columnId, Long taskId) {
        repository.updateTaskColumn(columnId, taskId);
    }
}
