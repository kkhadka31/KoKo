package com.koshish.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private long userId;
    private String firstName;
    private String secondName;
    private String fullName;
    private String email;
    private String gender;
    private String phoneNumber;
    private String password;
    private String userName;

    //todo: add Role to the table and modify mock values
    private Role role = Role.USER;
}
