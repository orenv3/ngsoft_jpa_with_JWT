package com.demo.ngsoft.responseObjects;

public record UserTableResponse(
        long user_id,
        String name,
        String email,
        boolean isAdmin,
        boolean active,
//        String password,
        String actionMade,
        String err
) {

}
