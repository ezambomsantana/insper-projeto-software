package com.insper.user.user;

import com.insper.user.user.dto.ReturnUserDTO;
import com.insper.user.user.dto.SaveUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping
    public List<ReturnUserDTO> listUsers() {
        return userService.listUsers();
    }

    @GetMapping("/teste")
    public String oi() {
        return "Oi!";
    }

    @PostMapping
    public ReturnUserDTO saveUser(@RequestBody SaveUserDTO saveUser) {
        return userService.saveUser(saveUser);
    }

    // @DeleteMapping  (/userId) -> Desasbilita o usuário - Apenas ADMIN
    // @GetMapping (/userId) -> Todos
    // @PutMapping (/userId) -> muda o password -> User só pode mudar o próprio passowrd


}
