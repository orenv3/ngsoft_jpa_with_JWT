package com.demo.ngsoft.controllers;


import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.requestObjects.CreateUserRequest;
import com.demo.ngsoft.requestObjects.UpdateUserRequest;
import com.demo.ngsoft.services.implementations.AdminUserImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Validated
@RequiredArgsConstructor
@RestController("/api/user/")
public class UserController {


    private final AdminUserImpl userImpl;



    @PostMapping("admin/createUser")
    public User create(@Valid @RequestBody() CreateUserRequest userObj) {
        return userImpl.createUser(userObj);
    }

    @DeleteMapping("admin/deleteUser/{id}")
    public String delete(@NotNull @Min(2) @PathVariable("id") long id) {//1 is default admin
        return userImpl.deleteUser(id);
    }

    @PutMapping("updateUser")
    public User update(@Valid @RequestBody() UpdateUserRequest updateObj) {
        return userImpl.updateUser(updateObj);
    }

    @GetMapping("admin/allUserList")
    public ResponseEntity<List<User>> getAllUserList(){
        List<User> userList = userImpl.getAllUserList();
        System.out.println(userList);
        return ResponseEntity.ok(userList);
    }


}
