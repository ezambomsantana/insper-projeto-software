package com.insper.user.user.dto;

import com.insper.user.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReturnUserDTO {
    private String email;
    private String passwrod;
    private List<String> roles;

    public static ReturnUserDTO convert(User user) {
        ReturnUserDTO userDTO = new ReturnUserDTO();
        userDTO.setEmail(user.getEmail());
        userDTO.setRoles(user.getRoles());
        userDTO.setPasswrod(user.getPassword());
        return userDTO;
    }

}
