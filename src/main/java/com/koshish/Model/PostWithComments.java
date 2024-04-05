package com.koshish.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PostWithComments {

    private int userId;
    private int personPostId;
    private int personCommentId;
    private String userName;
    private String post;
    private List<String> comment;
}
