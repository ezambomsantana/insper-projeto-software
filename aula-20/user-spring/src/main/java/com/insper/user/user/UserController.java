package com.insper.user.user;

import com.insper.user.user.dto.ReturnUserDTO;
import com.insper.user.user.dto.SaveUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public List<ReturnUserDTO> listUsers() {
        return userService.listUsers();
    }

    @PostMapping
    public ReturnUserDTO saveUser(Principal principal, @RequestBody SaveUserDTO saveUser) {
        System.out.println(principal.getName());
        return userService.saveUser(saveUser);
    }

}
