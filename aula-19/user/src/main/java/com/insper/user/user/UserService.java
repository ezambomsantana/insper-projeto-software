package com.insper.user.user;

import com.insper.user.user.dto.ReturnUserDTO;
import com.insper.user.user.dto.SaveUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public List<ReturnUserDTO> listUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(u -> ReturnUserDTO.convert(u))
                .collect(Collectors.toList());
    }

    public ReturnUserDTO saveUser(SaveUserDTO saveUser) {
        UserMongo userMongo = new UserMongo();
        String encoded = passwordEncoder.encode(saveUser.getPassword());
        userMongo.setPassword(encoded);
        userMongo.setEmail(saveUser.getEmail());
        userMongo.setRoles(saveUser.getRoles());

        return ReturnUserDTO.convert(userRepository.save(userMongo));
    }

    public ReturnUserDTO validateUser(String email, String password) {
        String encoded = DigestUtils
                .md5DigestAsHex(password.getBytes()).toUpperCase();
        UserMongo userMongo = userRepository.findByEmailAndPassword(email, encoded);
        if (userMongo == null) {
            throw new RuntimeException("User not found");
        }
        return ReturnUserDTO.convert(userMongo);
    }

}
