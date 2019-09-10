package edu.micronaut.service;

import edu.micronaut.model.User;
import io.micronaut.configuration.hibernate.jpa.scope.CurrentSession;
import io.micronaut.http.HttpStatus;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserService implements IUserService{


    @PersistenceContext
    //@Inject
    //@CurrentSession
    private EntityManager entityManager;

    public UserService(@CurrentSession EntityManager entityManager) {
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public HttpStatus save(@NotNull User user) {
        User user1= new User(user.getId(),user.getFirstName(),user.getLastName(),user.getPhoneNumber(),user.getEmail(),user.getPassword());
    entityManager.persist(user1);
        return HttpStatus.CREATED;
    }

    @Override
    @Transactional
    public Optional<User> findUserByFirstName(@NotNull String firstName) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        String sql="SELECT u FROM users as u";
        return entityManager.createQuery(sql,User.class).getResultList();
    }

    @Override
    @Transactional
    public Optional<User> findByUserId(@NotNull int id) {
        return Optional.ofNullable(entityManager.find(User.class,id));
    }

    @Override
    @Transactional
    public HttpStatus deleteUserById(@NotNull int id) {
        findByUserId(id).ifPresent(u->entityManager.remove(u));
        return HttpStatus.OK;
    }

    @Override
    @Transactional
    public Optional<User> findUserByEmail(@NotNull String email) {
        return Optional.ofNullable(entityManager.find(User.class,email));
    }


//    @Override
//    public User updateUser(int id, String name) {
//
//        entityManager.createQuery("UPDATE users u SET name=:name where id=:id")
//                .setParameter("name",name)
//                .setParameter("id",id)
//                .executeUpdate();
//
//    }
}
