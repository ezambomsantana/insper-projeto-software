package com.insper.user.common;

import com.insper.user.user.UserMongo;
import com.insper.user.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MongoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserMongo userMongo = userRepository.findByEmail(username);

        //String [] roles = new String[userMongo.getRoles().size()];
        //for (int i = 0 ; i < userMongo.getRoles().size(); i++) {
         //   roles[i] = userMongo.getRoles().get(i);
        //}

        return User.builder()
                .username(userMongo.getEmail())
                .password(userMongo.getPassword())
                .roles(userMongo.getRoles().toArray(String[]::new))
                .build();
    }

}
