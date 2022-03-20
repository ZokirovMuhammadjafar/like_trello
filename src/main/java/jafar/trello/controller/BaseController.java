package jafar.trello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jafar.trello.configs.security.UserDetails;
import jafar.trello.services.auth.AuthUserService;
import jafar.trello.services.project.ProjectService;
import jafar.trello.utils.SessionUser;

@Controller
public class BaseController {



    private final SessionUser user;
    private final ProjectService service;
    private final AuthUserService userService;

    public BaseController(SessionUser user, ProjectService service, AuthUserService userService) {
        this.user = user;
        this.service = service;
        this.userService = userService;
    }

    @RequestMapping(value = {"", "/", "/home"})
    public String home() {
        UserDetails details = user.getInstance();
        String code = details.getRole().getCode();

        if(code.equals("SUPERADMIN")){
            return "redirect:organization/list";
        }else{
            return "redirect:project/all/" + details.getOrganization();
        }
    }



    @PostMapping(value = {"/search"})
    public String search(Model model) {
        UserDetails details = user.getInstance();
        model.addAttribute("users", userService.getAll(details.getOrganization()));
        return "search";
    }

}
