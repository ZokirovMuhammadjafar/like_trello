package jafar.trello.services.organization;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import jafar.trello.dto.organization.OrganizationDto;
import jafar.trello.dto.organization.OrganizationUpdateDto;
import jafar.trello.entity.organization.Organization;
import jafar.trello.mappers.OrganizationMapper;
import jafar.trello.repositories.OrganizationRepository;
import jafar.trello.services.AbstractService;
import jafar.trello.services.GenericCrudService;
import jafar.trello.services.file.FileStorageService;
import jafar.trello.utils.FileUploadUtils;
import jafar.trello.validator.organization.OrganizationValidator;

import java.util.List;

@Service
public class OrganizationService extends AbstractService<OrganizationRepository, OrganizationMapper, OrganizationValidator> implements GenericCrudService<Organization, OrganizationDto, jafar.trello.dto.organization.OrganizationCreateDto, OrganizationUpdateDto, Long> {

    private final FileStorageService fileStorageService;

    protected OrganizationService(OrganizationRepository repository, OrganizationMapper mapper, OrganizationValidator validator, FileStorageService fileStorageService) {
        super(repository, mapper, validator);
        this.fileStorageService = fileStorageService;
    }


    public OrganizationUpdateDto getOrganization(Long id) {
        return mapper.toUpdateDto(repository.findOrganizationByIdAndDeletedFalse(id));
    }

    @Override
    public List<OrganizationDto> getAll() {
        for (Organization organization : repository.findAllByDeletedFalseOrderByIdAsc()) {
            System.out.println("organization = " + organization);
        }
        return mapper.toDto(repository.findAllByDeletedFalseOrderByIdAsc());
    }

    @Override
    public OrganizationDto get(Long id) {
        System.out.println("mapper.toDto(repository.findOrganizationByIdAndDeletedFalse(id)) = " + mapper.toDto(repository.findOrganizationByIdAndDeletedFalse(id)));
        return mapper.toDto(repository.findOrganizationByIdAndDeletedFalse(id));
    }


    @Override
    public Long create(jafar.trello.dto.organization.OrganizationCreateDto createDto) {
        MultipartFile logo = createDto.getLogo();
        Organization organization = mapper.fromCreateDto(createDto);
        organization.setStatus("ACTIVE");
        organization.setDeleted(false);
        organization.setLogo(FileUploadUtils.UPLOAD_DIRECTORY + fileStorageService.store(logo));
        return repository.save(organization).getId();
    }

    @Override
    public Void delete(Long id) {
        repository.deleteOrganization(id);
        return null;
    }

    @Override
    public Void update(OrganizationUpdateDto updateDto) {
        return null;
    }

    public Void update(OrganizationUpdateDto updateDto, Long id) {
        updateDto.setId(id);
        updateDto.setRegistrationNumber(repository.findOrganizationByIdAndDeletedFalse(id).getRegistrationNumber());
        updateDto.setStatus("ACTIVE");
        repository.save(mapper.fromUpdateDto(updateDto));
        return null;
    }

    public void block(Long id, boolean b) {
        Organization organization = repository.findOrganizationByIdAndDeletedFalse(id);
        organization.setBlocked(b);
        repository.save(organization);
        System.out.println("repository.findOrganizationByIdAndDeletedFalseOrderByIdAsc(id) = " + repository.findOrganizationByIdAndDeletedFalse(id));
    }
}
