package jafar.trello.configs.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import jafar.trello.entity.auth.AuthUser;
import jafar.trello.repositories.AuthUserRepository;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AuthUserRepository repository;

    public UserDetailsService(AuthUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = repository.getAuthUsersByUsernameAndDeletedFalse(username).orElseThrow(()->{throw  new RuntimeException("Error");});
        return new jafar.trello.configs.security.UserDetails(user);
    }
}
