package com.demo.ngsoft.dao.services;

import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.repositories.UserRepo;
import com.demo.ngsoft.requestObjects.UpdateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service("AdminUserImpl")
public class UserService {

    private final UserRepo userRepo;

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

    public List<User> getAllUserListWithPageRequest(int pageNo, int pageSize){
        Pageable pageable =  PageRequest.of(pageNo-1,pageSize);
        return userRepo.findAll(pageable).getContent();

    }

    public User getUserById(long id){
        return userRepo.getReferenceById(id);
    }
    public Optional<User> findUserByEmail(String email){
        return userRepo.findByEmail(email);
    }

}
