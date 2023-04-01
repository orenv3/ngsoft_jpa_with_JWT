package com.demo.ngsoft.dao.services;

import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.repositories.UserRepo;
import com.demo.ngsoft.requestObjects.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("AdminUserImpl")
public class UserService {

    private final UserRepo userRepo;

   /* public User createUser(CreateUserRequest userObj PasswordEncoder passwordEncoder){
        Optional<User> checkDuplication = userRepo.findByEmail(registerRequest.email());
        if(checkDuplication.isPresent())
            throw new ValidationErrorException("The user: "+registerRequest.email()+" already exists");//return new AuthResponse("This user already exists");
        User user = new User(registerRequest);
        user.setRole(Role.chooseRole(user.isAdmin()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return this.generateTokenByUser(user);
    }*/
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
        List<User> usersList = userRepo.findAll();
        return usersList;
    }

    public User getUserById(long id){
        return userRepo.getReferenceById(id);
    }
    public Optional<User> findUserByEmail(String email){
        return userRepo.findByEmail(email);
    }
}
