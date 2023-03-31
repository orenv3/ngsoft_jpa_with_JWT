package com.demo.ngsoft.controllers;


import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.requestObjects.CreateUserRequest;
import com.demo.ngsoft.requestObjects.UpdateUserRequest;
import com.demo.ngsoft.services.implementations.UserImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/user/")
@RestController
public class UserController {

    private final UserImpl userImpl;

    /**
     * Admin privilege
     * create user
     * @param userObj CreateUserRequest objectwith user details
     * @return UserObject
     */
    @PostMapping("admin/createUser")
    public User create(@Valid @RequestBody() CreateUserRequest userObj) {
        return userImpl.createUser(userObj);
    }

    /**
     * Admin privilege
     * Delete user by ID
     * @param id User id
     * @return
     */
    @DeleteMapping("admin/deleteUser/{id}")
    public String delete(@NotNull @Min(2) @PathVariable("id") long id) {//1 is default admin
        return userImpl.deleteUser(id);
    }

    /**
     * Admin privilege
     * update user details
     * @param updateObj
     * @return
     */
    @PutMapping("admin/updateUser")
    public User update(@Valid @RequestBody() UpdateUserRequest updateObj) {
        return userImpl.updateUser(updateObj);
    }

    /**
     * Admin privilege
     * Get list of all user in DB
     * @return
     */
    @GetMapping("admin/allUserList")
    public List<User> getAllUserList(){
        List<User> userList = userImpl.getAllUserList();

        return userList;
    }


}
