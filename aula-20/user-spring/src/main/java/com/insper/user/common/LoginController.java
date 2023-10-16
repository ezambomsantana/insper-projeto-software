package com.insper.user.common;

import com.insper.user.common.JwtUtils;
import com.insper.user.user.LoginService;
import com.insper.user.user.UserService;
import com.insper.user.user.dto.LoginDTO;
import com.insper.user.user.dto.ReturnUserDTO;
import com.insper.user.user.dto.TokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.apache.commons.lang3.RandomStringUtils;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;


    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public TokenDTO login(@RequestBody LoginDTO loginDTO) {


        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        User user = (User) authentication.getPrincipal();

            String token = jwtUtil.createToken(user.getUsername());


            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setEmail(user.getUsername());
            tokenDTO.setToken(token);
          //  tokenDTO.setRoles(user.getRoles());
            return tokenDTO;


    }

    // GetMapping("/token/{token}")

}
