package com.insper.user.user;

import com.insper.user.user.dto.ReturnUserDTO;
import com.insper.user.user.dto.SaveUserDTO;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public List<ReturnUserDTO> listUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(u -> ReturnUserDTO.convert(u))
                .collect(Collectors.toList());
    }

    public ReturnUserDTO saveUser(SaveUserDTO saveUser) {
        User user = new User();
        String encoded = DigestUtils
                .md5DigestAsHex(saveUser.getPassword().getBytes()).toUpperCase();
        user.setPassword(encoded);
        user.setEmail(saveUser.getEmail());
        user.setRoles(saveUser.getRoles());

        return ReturnUserDTO.convert(userRepository.save(user));
    }

    public ReturnUserDTO validateUser(String email, String password) {
        String encoded = DigestUtils
                .md5DigestAsHex(password.getBytes()).toUpperCase();
        User user = userRepository.findByEmailAndPassword(email, encoded);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        return ReturnUserDTO.convert(user);
    }

}
