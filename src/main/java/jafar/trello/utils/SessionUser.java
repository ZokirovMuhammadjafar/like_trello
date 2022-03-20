package jafar.trello.utils;

import jafar.trello.configs.security.UserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class SessionUser {
    private Long Id;
    private Long orgId;
    private String code;

    public UserDetails getInstance() {
        return  (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    }
    public Long getId() {
        return getInstance().getId();
    }



    public Long getOrgId() {
        return getInstance().getOrganization();
    }


    public String getCode() {
        return getInstance().getRole().getCode();
    }

}
