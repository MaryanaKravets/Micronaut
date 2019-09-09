package edu.micronaut.service;

import edu.micronaut.model.User;
import io.micronaut.context.annotation.Prototype;
import io.micronaut.http.HttpStatus;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Singleton
//@Prototype
public class UserService implements IUserService{//, ApplicationEventListener {

//    public List<User> userList=new ArrayList<>(
//            Arrays.asList(
//                    new User(1,"Rory", "Power", 678998,"rory@mail.com","rory"),
//                    new User(2,"Say", "Horstmann",344558,"say@mail.com","say"),
//                    new User(3,"Scott", "Chacon",343445,"scott@mail.com","scott"),
//                    new User(4,"Crag", "Walls",342312,"crag@ukr.net","crag"),
//                    new User(5,"Stiven", "King",122334,"stiven@mail.com","stiven"),
//                    new User(6,"Adrian", "McKinty",334567,"adrian@ukr.net","adrian"),
//                    new User(7,"Eliza", "Lim",789009,"eliza@mail.com","eliza"),
//                    new User(8,"Zara", "Raheem",445566,"zara@ukr.net","zara"),
//                    new User(9,"Rafael", "Badziag",899876,"rafael@mail.com","rafael")
//            ));

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public HttpStatus save(User user) {
//        User user1= new User(user.getId(),user.getFirstName(),user.getLastName(),user.getPhoneNumber(),user.getEmail(),user.getPassword());
//        userList.add(user1);
//        return HttpStatus.CREATED;
        User user1= new User(user.getId(),user.getFirstName(),user.getLastName(),user.getPhoneNumber(),user.getEmail(),user.getPassword());
    entityManager.persist(user1);
        return HttpStatus.CREATED;
    }

    @Override
    public Optional<User> findUserByFirstName(String firstName) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {

        //return userList;
        String sql="SELECT u FROM users as u";
        return entityManager.createQuery(sql,User.class).getResultList();
    }

    @Override
    public Optional<User> findByUserId(int id) {

        //return userList.stream().filter(user -> user.getId()==id).findAny();
        return Optional.ofNullable(entityManager.find(User.class,id));
    }

    @Override
    public HttpStatus deleteUserById(int id) {
//     userList.removeIf(user -> user.getId()==id);
//     return HttpStatus.OK;
        findByUserId(id).ifPresent(u->entityManager.remove(u));
        return HttpStatus.OK;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return Optional.ofNullable(entityManager.find(User.class,email));
    }

//    @Override
//    public void onApplicationEvent(Object event) {
//
//    }

//    @Override
//    public User updateUser(int id, String name) {
//        User user1=new User();
//        return userList.stream().filter(user2 -> user2.getId()==id).map(user2 -> (user2.setFirstName(user.getFirstName());
//        user2.setLastName(user.getLastName()); user2.setEmail(user.getEmail()); user2.setPhoneNumber(user.getPhoneNumber());
//        user2.setPassword(user.getPassword()); return user2;)).findAny().get();
////        return null;
//
//        entityManager.createQuery("UPDATE users u SET name=:name where id=:id")
//                .setParameter("name",name)
//                .setParameter("id",id)
//                .executeUpdate();
//
//    }
}
