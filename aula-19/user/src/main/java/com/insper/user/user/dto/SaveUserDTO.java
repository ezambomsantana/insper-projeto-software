package com.insper.user.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SaveUserDTO {
    private String email;
    private String password;
    private List<String> roles;
}
