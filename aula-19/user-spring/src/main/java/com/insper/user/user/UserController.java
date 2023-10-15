package com.insper.user.user;

import com.insper.user.user.dto.ReturnUserDTO;
import com.insper.user.user.dto.SaveUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<ReturnUserDTO> listUsers() {
        return userService.listUsers();
    }

    @PostMapping
    public ReturnUserDTO saveUser(@RequestBody SaveUserDTO saveUser) {
        return userService.saveUser(saveUser);
    }

}
