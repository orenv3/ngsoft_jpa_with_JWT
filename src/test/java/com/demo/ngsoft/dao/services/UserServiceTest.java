package com.demo.ngsoft.dao.services;

import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.repositories.UserRepo;
import com.demo.ngsoft.requestObjects.CreateUserRequest;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceTest {

    @Mock
    private UserRepo userRepo;
    private AutoCloseable autoCloseable;
    private UserService userServiceUnderTest;


    @BeforeEach
    public void setup() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userServiceUnderTest = new UserService(userRepo);

        when(userRepo.findAll()).thenReturn(this.getUserList());
        when(userRepo.findByEmail(any())).thenReturn(Optional.ofNullable(this.getUserPrivilge()));
        when(userRepo.getReferenceById(3L)).thenReturn(this.getUserPrivilge());
        when(userRepo.getReferenceById(null)).thenReturn(null);
        ;

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void updateUser() {

    }

    @Test
    void getAllUserList() {
        //when
        List<User> usersListUnderTest = userServiceUnderTest.getAllUserList();
        //then verify
        verify(userRepo).findAll();


        //verify that user's email is unique
        Set<String> checkEmailDuplication = new HashSet<>();
        Optional<User> isDuplicate = usersListUnderTest.parallelStream()
                .filter(usr -> !(checkEmailDuplication.add(usr.getEmail())))
                .findFirst();

        assertThat(isDuplicate).isEqualTo(Optional.empty());
    }

    @Test
    void getUserById() {
        //when
        long idTest = 3;
        User userUnderTest = userServiceUnderTest.getUserById(idTest);

        //then
        verify(userRepo).getReferenceById(idTest);
        assertThat(userUnderTest.getId()).isEqualTo(idTest);
        assertThat(userUnderTest.getEmail()).isEqualTo(getUserPrivilge().getEmail());

    }

    @Test
    void findUserByEmail() {

        //when
        long idTest = 3;
        User userUnderTest = userServiceUnderTest.getUserById(idTest);
        String emailUnderTest = userUnderTest.getEmail();

        //then
        verify(userRepo).getReferenceById(idTest);
        assertThat(userUnderTest.getId()).isEqualTo(idTest);
        Optional<User> userEmailUnderTest = userServiceUnderTest.findUserByEmail(emailUnderTest);//emailUnderTest
        verify(userRepo).findByEmail(emailUnderTest);
        assertThat(userEmailUnderTest).isPresent();
        assertThat(userEmailUnderTest.get()).isEqualTo(userUnderTest);


    }

    @Test
    void deleteUser() {
        long indexToDel = 3;
        String ansTest = userServiceUnderTest.deleteUser(indexToDel);
        verify(userRepo).deleteById(indexToDel);
        assertThat(userRepo.getReferenceById(null)).isNull();
        assertThat(ansTest).isEqualTo("Deleted: true");
    }





    /// **************************  set of DB methods **************************  ///




    public  List<User> getUserList(){
        CreateUserRequest userRequest1 = new CreateUserRequest(
                "oren","oren@email1",
                true,true,
                "pass");
        ;



        CreateUserRequest userRequest2 = new CreateUserRequest(
                "avivit","avivit@email1",
                true,true,
                "pass");



        CreateUserRequest userRequest3 = new CreateUserRequest(
                "maya","maya@email1",
                false,true,
                "pass");


        CreateUserRequest userRequest4 = new CreateUserRequest(
                "Daniel","Daniel@email1",
                false,true,
                "pass");


        CreateUserRequest userRequest5 = new CreateUserRequest(
                "raz","raz@email1",
                false,true,
                "pass");

    List<User> userList = List.of(new User(userRequest1),new User(userRequest2)
        ,new User(userRequest3),new User(userRequest4)
        ,new User(userRequest5));



      return userList;


    }
    public  User getAdminUser(){
        CreateUserRequest userRequest1 = new CreateUserRequest(
                "oren","oren@email1",
                true,true,
                "pass");
        User user = new User(userRequest1);
        user.setId(1L);
        return user;

    }
    public  User getUserPrivilge(){
        CreateUserRequest userRequest3 = new CreateUserRequest(
                "maya","maya@email1",
                false,true,
                "pass");

        User user = new User(userRequest3);
        user.setId(3L);
        return user;
    }

    /*
    public void setUsersToDb(){

        CreateUserRequest userRequest1 = new CreateUserRequest(
                "oren","oren@email1",
                true,true,
                "pass");
        userRepoDbBuilder.save(new User(userRequest1));

        CreateUserRequest userRequest2 = new CreateUserRequest(
                "avivit","avivit@email1",
                true,true,
                "pass");
        userRepoDbBuilder.save(new User(userRequest2));

        CreateUserRequest userRequest3 = new CreateUserRequest(
                "maya","maya@email1",
                false,true,
                "pass");
        userRepoDbBuilder.save(new User(userRequest3));

        CreateUserRequest userRequest4 = new CreateUserRequest(
                "Daniel","Daniel@email1",
                false,true,
                "pass");
        userRepoDbBuilder.save(new User(userRequest4));

        CreateUserRequest userRequest5 = new CreateUserRequest(
                "raz","raz@email1",
                false,true,
                "pass");
        userRepoDbBuilder.save(new User(userRequest5));


        System.out.println(
                userRepoDbBuilder.findAll()
        );

    }*/
}