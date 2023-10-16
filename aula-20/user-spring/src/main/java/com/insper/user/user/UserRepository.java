package com.insper.user.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

    boolean existsByEmailAndPassword(String email, String password);

    User findByEmailAndPassword(String email, String encoded);

    User findByEmail(String username);
}
