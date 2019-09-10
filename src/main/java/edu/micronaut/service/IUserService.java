package edu.micronaut.service;

import edu.micronaut.model.User;
import io.micronaut.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

public interface IUserService {

    HttpStatus save(@NotNull User user);

    Optional<User> findUserByFirstName(@NotNull String firstName);

    List<User> findAll();

    Optional<User> findByUserId(@NotNull int id);

    HttpStatus deleteUserById(@NotNull int id);

    Optional<User> findUserByEmail(@NotNull String email);

//    User updateUser(int id,User user);
}
