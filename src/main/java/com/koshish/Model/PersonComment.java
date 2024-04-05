package com.koshish.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PersonComment {

    private int personCommentId;
    private int personPostId;
    private int userId;
    private String userName;
    private String comment;
}
