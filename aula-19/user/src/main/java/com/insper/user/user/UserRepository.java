package com.insper.user.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserMongo, String> {

    boolean existsByEmailAndPassword(String email, String password);

    UserMongo findByEmailAndPassword(String email, String encoded);

    UserMongo findByEmail(String username);
}
