package com.koshish.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserCredentials {

    private int userId;
    private String userName;
    private String password;
    private byte[] salt;

}
