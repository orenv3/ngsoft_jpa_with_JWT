package com.demo.ngsoft.services.implementations;

import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.repositories.UserRepo;
import com.demo.ngsoft.requestObjects.CreateUserRequest;
import com.demo.ngsoft.requestObjects.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("UserTypeImpl")
public class UserTypeImpl {
 /* UserTableResponse response = new UserTableResponse(userResponse.getId(),
                userResponse.getName(),
                userResponse.getEmail(),
                userResponse.isAdmin(),
                userResponse.isActive(),
                "create new user",
                userResponse==null? "Something went wrong. The user did not create successfully.":"User created successfully");*/

    @Autowired
    UserRepo userRepo;

    public User createUser(CreateUserRequest userObj){
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
        List<User> usersList = userRepo.findAll();
        return usersList;
    }
}
