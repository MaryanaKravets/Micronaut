package edu.micronaut.controller;

import edu.micronaut.model.User;
import edu.micronaut.service.IUserService;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@Secured(SecurityRule.IS_AUTHENTICATED)
public class UserController {

    @Inject
    private IUserService iUserService;

    //@Produces(MediaType.TEXT_PLAIN)
//    @Get("/user/{firstName}")
//   // @RolesAllowed("ROLE_ADMIN")
//    public Optional<User> findUserByFirstName(String firstName) {
//
//        return iUserService.findUserByFirstName(firstName);
//    }

   // @Produces(MediaType.TEXT_PLAIN)
    @Get("/user")
    //@PermitAll
    public List<User> findAllUsers() {

        return iUserService.findAll();
    }

   // @Produces(MediaType.TEXT_PLAIN)
    @Get("/user/{id}")
    public Optional<User> findUserById(int id) {

        return iUserService.findByUserId(id);
    }

    @Delete("/user/{id}")
    public HttpStatus deleteUserById(int id) {

        return iUserService.deleteUserById(id);
    }

    @Post("/user")
    public HttpStatus saveUser(@Body @Valid User user) {

        return iUserService.save(user);
    }
}