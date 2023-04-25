package com.demo.ngsoft.repositories;

import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.requestObjects.CreateUserRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@NoArgsConstructor
@AllArgsConstructor
@DataJpaTest
@Component("UserRepoTest")
public class UserRepoTest {

    @Autowired
    private UserRepo userRepoUnderTest;


    @Test
    void findByEmail() {
        //given

        CreateUserRequest userRequest = new CreateUserRequest(
                "oren","email1",
                true,true,
                "pass");
        User user = new User(userRequest);
        //when
        user = userRepoUnderTest.save(user);
        Optional<User> expcted = userRepoUnderTest.findByEmail(user.getEmail());

        //then
       assertEquals(user,expcted.get());

    }

    @Test
    void findByEmailNotExistUser() {
        CreateUserRequest userRequest = new CreateUserRequest(
                "oren","email1",
                true,true,
                "pass");
        User user = new User(userRequest);
        //when
        user = userRepoUnderTest.save(user);
        Optional<User> exists = userRepoUnderTest.findByEmail(user.getEmail());

        Optional<User> empty = userRepoUnderTest.findByEmail("noSuchEmail@.gogo.com");
        Optional<User> tstExist = userRepoUnderTest.findByEmail(exists.get().getEmail());
        assertTrue(empty.isEmpty());
    }


    public  User getReferenceById(long id){
        return userRepoUnderTest.getReferenceById(id);
    }




}