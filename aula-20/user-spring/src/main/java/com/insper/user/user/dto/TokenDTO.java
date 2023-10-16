package com.insper.user.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TokenDTO {

    private String email;
    private String token;
    private List<String> roles;
}
