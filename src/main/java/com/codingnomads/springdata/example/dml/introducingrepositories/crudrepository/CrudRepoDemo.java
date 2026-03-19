/* CodingNomads (C)2024 */
package com.codingnomads.springdata.example.dml.introducingrepositories.crudrepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudRepoDemo implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CrudRepoDemo.class);
    }

    // autowire the UserRepo into the class to gain access to the CRUD methods
    @Autowired
    UserRepo userRepo;

    @Override
    public void run(String... args) throws Exception {
        // create new user
        User user =
                User.builder().firstName("Bobby").lastName("Bobbert").age(56).build();
        User user2 =
                User.builder().firstName("Joanne").lastName("Joanna").age(36).build();
        User user3 =
                User.builder().firstName("Alicia").lastName("R").age(26).build();
        User user4 =
                User.builder().firstName("Michael").lastName("S").age(27).build();


        // save user and assign what is returned to the user variable.
        user = userRepo.save(user);
        user2 = userRepo.save(user2);
        user3 = userRepo.save(user3);
        user4 = userRepo.save(user4);

        Iterable<User> users = userRepo.findAll();

        for (User u : users) {
            System.out.println(u.toString());
        }

        // delete the user using the id of the inserted user object
        userRepo.deleteById(user.getId());
        userRepo.deleteById(user2.getId());
        userRepo.deleteById(user3.getId());
        userRepo.deleteById(user4.getId());
    }
}
