package com.insper.user.user.dto;

import com.insper.user.user.UserMongo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReturnUserDTO {
    private String email;
    private String password;
    private List<String> roles;

    public static ReturnUserDTO convert(UserMongo userMongo) {
        ReturnUserDTO userDTO = new ReturnUserDTO();
        userDTO.setEmail(userMongo.getEmail());
        userDTO.setRoles(userMongo.getRoles());
        userDTO.setPassword(userMongo.getPassword());
        return userDTO;
    }

}
