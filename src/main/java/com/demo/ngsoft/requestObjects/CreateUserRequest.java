package com.demo.ngsoft.requestObjects;

import com.demo.ngsoft.entities.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;


public record CreateUserRequest(@NotBlank @Size(max=15) String name,
                                @NotBlank String email,
                                @NotNull boolean isAdmin,
                                @NotNull boolean active,
                                @NotBlank String password) {
}
