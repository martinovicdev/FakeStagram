package hr.martinovic.FakeStagram.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import hr.martinovic.FakeStagram.model.Authorities;
import hr.martinovic.FakeStagram.model.Users;
import hr.martinovic.FakeStagram.db.AuthoritiesRepository;
import hr.martinovic.FakeStagram.db.UserRepository;
import hr.martinovic.FakeStagram.utils.IPermission;
import hr.martinovic.FakeStagram.utils.UserPermissionFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("newUser")
public class RegistrationController {

    private Logger log = LoggerFactory.getLogger(ImageController.class);

    private final UserRepository userRepository;
    private final AuthoritiesRepository authoritiesRepository;

    public RegistrationController(UserRepository userRepository, AuthoritiesRepository authoritiesRepository) {
        this.userRepository = userRepository;
        this.authoritiesRepository = authoritiesRepository;
    }

    @GetMapping
    public String showNewRegistrationForm(Model model){
        model.addAttribute("user", new Users());
        model.addAttribute("userTypes", Users.Roles.values());
        return "newUserForm";
    }

    @PostMapping
    public String createNewUser (@Valid Users user, Authorities authority, Errors errors, Model model){

        if (errors.hasErrors()){
            model.addAttribute("userTypes", Users.Roles.values());
            return "newUserForm";
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10)));
        userRepository.save(user);
        UserPermissionFactory userPermissionFactory = new UserPermissionFactory();
        IPermission permission= userPermissionFactory.getPermission(user.getPermission());
        authority.setUsername(user.getUsername());
        authority.setAuthority(permission.addRole());
        authoritiesRepository.save(authority);
        log.info(user.verifyCreation());
        return "login";
    }

}
