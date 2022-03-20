package jafar.trello.services.auth;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jafar.trello.dto.auth.*;
import jafar.trello.entity.auth.AuthRole;
import jafar.trello.entity.auth.AuthUser;
import jafar.trello.entity.auth.Token;
import jafar.trello.exceptions.NotFoundException;
import jafar.trello.mappers.AuthUserMapper;
import jafar.trello.repositories.AuthRoleRepository;
import jafar.trello.repositories.AuthUserRepository;
import jafar.trello.repositories.TokenRepository;
import jafar.trello.services.AbstractService;
import jafar.trello.services.GenericCrudService;
import jafar.trello.services.GenericService;
import jafar.trello.utils.SendEmail;
import jafar.trello.validator.auth.AuthUserValidator;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthUserService
        extends AbstractService<
        AuthUserRepository,
        AuthUserMapper,
        AuthUserValidator>
        implements GenericService<AuthDto, Long>,
        GenericCrudService<AuthUser, AuthDto, AuthUserCreateDto, AuthUserUpdateDto, Long> {
    private final TokenRepository tokenRepository;
    private final AuthRoleRepository roleRepository;
    private final SendEmail email;
    private final PasswordEncoder encoder;


    protected AuthUserService(AuthUserRepository repository,
                              AuthUserMapper mapper,
                              AuthUserValidator validator,
                              TokenRepository tokenRepository, AuthRoleRepository roleRepository, SendEmail email, PasswordEncoder encoder) {
        super(repository, mapper, validator);
        this.tokenRepository = tokenRepository;
        this.roleRepository = roleRepository;
        this.email = email;
        this.encoder = encoder;
    }

    @Override
    public Long create(AuthUserCreateDto createDto) {
        AuthUser authUser = mapper.fromCreateDto(createDto);
        authUser.setActive(true);
        authUser.setOrganizationId(createDto.getOrganizationId());
        AuthRole byId = roleRepository.getById(createDto.getUserRole());
        authUser.setRole(byId);
        authUser.setPassword(encoder.encode(createDto.getPassword()));
        return repository.save(authUser).getId();
    }


    public Long createAdmin(AddAdminDto addAdminDto, Long id) {
        AuthUser authUser = mapper.fromDto(addAdminDto);
        AuthRole byId = roleRepository.getById(2L);
        authUser.setOrganizationId(id);
        authUser.setRole(byId);
        authUser.setActive(true);
        authUser.setPassword(encoder.encode(addAdminDto.getPassword()));
        return repository.save(authUser).getId();
    }

    @Override
    public Void delete(Long id) {
        repository.deleteById(id);
        return null;
    }

    public Void delete(String username, String password) {
        Optional<AuthUser> user = repository.getAuthUsersByUsernameAndDeletedFalse(username);
        if (!user.isPresent() || !encoder.matches(password, user.get().getPassword()))
            throw new NotFoundException("bad Credential");
        repository.deleteUser(user.get().getId(), user.get().getUsername() + "" + System.currentTimeMillis());
        return null;
    }


    @Override
    public Void update(AuthUserUpdateDto dto) {
        repository.updateUser(dto.getFirstName(),dto.getLastName(),dto.getEmail(),dto.getPhone(),dto.getUsername(),dto.getPhotoPath(),dto.getId());
        return null;
    }


    public List<AuthDto> getAll(Long orgId) {
        return mapper.toDto(repository.findAllByOrganizationIdAndDeletedFalse(orgId));
    }

    @Override
    public List<AuthDto> getAll() {
        return null;
    }

    public AuthDto get(Long id) {
        AuthUser user = repository.findByIdAndDeletedFalse(id).get();
        return mapper.toDto(user);
    }


    public AuthUser get(String username) {
        Optional<AuthUser> authUserByUsername = repository.getAuthUsersByUsernameAndDeletedFalse(username);
        return authUserByUsername.get();
    }

    public void sendMail(String emailUser, String username) throws NotFoundException {
        if (Objects.nonNull(emailUser) && emailUser.equals(get(username).getEmail())) {
            String tokenGenerate = UUID.randomUUID().toString().replace("-", "");
            Token token = new Token();
            token.setPrivateToken(tokenGenerate);
            tokenRepository.save(token);
            email.sendEmail(emailUser, tokenGenerate);
        }

    }

    public boolean checkToken(String token) {
        return !tokenRepository.findByPrivateToken(token).isPresent();

    }

    public void resetPassword(ResetPassword dto) {
        AuthUser authUser = get(dto.getUsername());
        authUser.setPassword(encoder.encode(dto.getPassword()));
        repository.save(authUser);
    }


    public void savePhoto(String store,Long id) {
        repository.image(store,id);
    }
}
