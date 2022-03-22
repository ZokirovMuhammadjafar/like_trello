package jafar.trello;

import jafar.trello.entity.auth.AuthRole;
import jafar.trello.entity.auth.AuthUser;
import jafar.trello.repositories.AuthRoleRepository;
import jafar.trello.repositories.AuthUserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@SpringBootApplication
public class TrelloApplication {


    public static void main(String[] args) {
        SpringApplication.run(TrelloApplication.class, args);
    }

    private final AuthUserRepository authUserRepository;
    private final AuthRoleRepository authRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public TrelloApplication(AuthUserRepository authUserRepository,
                               AuthRoleRepository authRoleRepository,
                               PasswordEncoder passwordEncoder
    ) {
        this.authUserRepository = authUserRepository;
        this.authRoleRepository = authRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(timeout = 10)
//    @Bean
    public void run() throws Exception {
        AuthUser admin = new AuthUser();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        System.out.println(admin.getPassword());
        AuthRole adminRole = authRoleRepository.findAuthRoleByCode("ADMIN").get();
        admin.setRole(adminRole);


        authUserRepository.save(admin);


        AuthUser manager = new AuthUser();
        manager.setUsername("user");
        manager.setPassword(passwordEncoder.encode("user123"));
        AuthRole managerRole = authRoleRepository.findAuthRoleByCode("USER").get();
        manager.setRole(managerRole);
        authUserRepository.save(manager);

    }
}
