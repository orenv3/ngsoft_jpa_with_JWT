package com.demo.ngsoft.services.implementations;

import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.errorHandler.ValidationErrorException;
import com.demo.ngsoft.repositories.UserRepo;
import com.demo.ngsoft.requestObjects.CreateUserRequest;
import com.demo.ngsoft.requestObjects.UpdateUserRequest;
import lombok.RequiredArgsConstructor;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("AdminUserImpl")
public class AdminUserImpl {

    private final UserRepo userRepo;
   // private final AuthenticationService authenticationService;

    public User createUser(CreateUserRequest userObj){
        //check if email exists
        //check if the executor is ADMIN


        Optional<User> isUser = userRepo.findByEmail(userObj.email());
        if(isUser.isPresent())
           throw new ValidationErrorException("The user already exist");
        User user = new User(userObj);
        User userResponse =  userRepo.save(user);
       return userResponse;
    }
    public User updateUser(UpdateUserRequest updateObj){
        User user = userRepo.getReferenceById(updateObj.id());
        user = updateObj.updateUserParameters(updateObj,user);
        User userResponse =  userRepo.save(user);
       return userResponse;
    }

    public String deleteUser(long id){
        User user = userRepo.getReferenceById(id);
        userRepo.deleteById(id);
        return "Deleted: "+!(userRepo.existsById(id));
    }


    public List<User> getAllUserList(){
        Optional<User> isUser = userRepo.findByEmail("aaa");
       // userRepo.findByEmail("aaa");
        List<User> usersList = userRepo.findAll();
        return usersList;
    }
}
