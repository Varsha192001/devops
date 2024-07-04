package com.gcrm.devopsreports.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gcrm.devopsreports.hobjects.User;


public interface UserRepository extends MongoRepository<User, String> {

	Optional<User> findByUserName(String username);

 

}
