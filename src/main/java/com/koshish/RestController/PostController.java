package com.koshish.RestController;

import com.koshish.Model.PersonPost;
import com.koshish.Model.PostWithComments;
import com.koshish.Repository.PersonPostDLImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rc")
public class PostController {

    private PersonPostDLImpl personPostDL = new PersonPostDLImpl();

    @PostMapping("/post")
    public ResponseEntity<PersonPost> addPost(@RequestBody PersonPost personPost) throws SQLException {
        personPost = personPostDL.addPersonPost(personPost);
        if (personPost != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(personPost);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PersonPost>> getAllPosts() throws SQLException {
        List<PersonPost> personPostList = new ArrayList<>();
        personPostList = personPostDL.getAllPeoplePost();
        if (personPostList != null) {
            return ResponseEntity.ok(personPostList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/post/{personPostId}")
    public ResponseEntity<PersonPost> getPostByPersonPostId(@PathVariable int personPostId) throws SQLException {
        PersonPost personPost = personPostDL.getPostByPersonPostId(personPostId);
        if (personPost != null) {
            return ResponseEntity.ok(personPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/editPosts")
    public ResponseEntity<PersonPost> editPost(@RequestBody PersonPost personPost) throws SQLException {
        personPost = personPostDL.editPost(personPost);
        if (personPost != null) {
            return ResponseEntity.ok(personPost);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/postWithComments/{personPostId}")
    public ResponseEntity<PostWithComments> getPostWithComments(@PathVariable int personPostId) throws SQLException {
        PostWithComments postWithComments = personPostDL.getPostWithComments(personPostId);
        if (postWithComments != null) {
            return ResponseEntity.ok(postWithComments);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/postsWithComments/{userId}")
    public ResponseEntity<List<PostWithComments>> getPostsWithCommentsByUserId(@PathVariable int userId) throws SQLException {
        List<PostWithComments> postWithCommentsList = personPostDL.getPostsWithCommentByUserId(userId);
        if (postWithCommentsList != null) {
            return ResponseEntity.ok(postWithCommentsList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/postsWithComments")
    public ResponseEntity<List<PostWithComments>> getPostsWithComments() throws SQLException {
        List<PostWithComments> postWithCommentsList = personPostDL.getPostsWithComments();
        if (postWithCommentsList != null) {
            return ResponseEntity.ok(postWithCommentsList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deletePost/{personPostId}")
    public ResponseEntity<String> deletePostByPersonPostId(@PathVariable int personPostId) throws SQLException {
        boolean postDeleted = personPostDL.deletePostByPersonPostId(personPostId);
        if (postDeleted) {
            return ResponseEntity.ok("Post deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post couldn't deleted");
        }
    }
}
