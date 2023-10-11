package com.insper.user.user;

import com.insper.user.user.dto.ReturnUserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class LoginService {

    private HashMap<String, ReturnUserDTO> cache = new HashMap<>();

    public ReturnUserDTO get(String token) {
        return cache.get(token);
    }

    public void put(String token, ReturnUserDTO user) {
        cache.put(token, user);
    }
}
