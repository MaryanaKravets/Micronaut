package edu.micronaut.service;

import edu.micronaut.model.User;
import io.micronaut.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public interface IUserService {

    HttpStatus save(User user);

    Optional<User> findUserByFirstName(String firstName);

    List<User> findAll();

    Optional<User> findByUserId(int id);

    HttpStatus deleteUserById(int id);

    Optional<User> findUserByEmail(String email);

//    User updateUser(int id,User user);
}
