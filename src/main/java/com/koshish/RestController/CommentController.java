package com.koshish.RestController;

import com.koshish.Model.PersonComment;
import com.koshish.Repository.PersonCommentDLImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/rc")
public class CommentController {

    private PersonCommentDLImpl personCommentDL = new PersonCommentDLImpl();

    @PostMapping("/addComment")
    public ResponseEntity<PersonComment> addComment(@RequestBody PersonComment personComment) throws SQLException {
        personComment = personCommentDL.addComment(personComment);
        if (personComment != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(personComment);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/comments")
    public ResponseEntity<List<PersonComment>> getAllComments() throws SQLException {
        List<PersonComment> personCommentList = personCommentDL.getAllPeopleComment();
        if (personCommentList != null) {
            return ResponseEntity.ok(personCommentList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/postComments/{personPostId}")
    public ResponseEntity<List<PersonComment>> getCommentsByPersonPostId(@PathVariable int personPostId) throws SQLException {
        List<PersonComment> personCommentList = personCommentDL.getCommentsByPersonPostId(personPostId);
        if (personCommentList != null) {
            return ResponseEntity.ok(personCommentList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/userComments/{userId}")
    public ResponseEntity<List<PersonComment>> getCommentsByUserId(@PathVariable int userId) throws SQLException {
        List<PersonComment> personCommentList = personCommentDL.getCommentsByUserId(userId);
        if (personCommentList != null) {
            return ResponseEntity.ok(personCommentList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/comment/{personCommentId}")
    public ResponseEntity<PersonComment> getCommentByPersonCommentId(@PathVariable int personCommentId) throws SQLException {
        PersonComment personCommentList = personCommentDL.getCommentByPersonCommentId(personCommentId);
        if (personCommentList != null) {
            return ResponseEntity.ok(personCommentList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PatchMapping("/editComment")
    public ResponseEntity<PersonComment> editCommentByPersonCommentId(@RequestBody PersonComment personComment) throws SQLException {
        PersonComment personCommentList = personCommentDL.editCommentByPersonCommentId(personComment);
        if (personCommentList != null) {
            return ResponseEntity.ok(personCommentList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deleteComment/{personCommentId}")
    public ResponseEntity<String> deleteCommentByPersonCommentId(@PathVariable int personCommentId) throws SQLException {
        boolean commentDeleted = personCommentDL.deleteCommentByPersonCommentId(personCommentId);
        if (commentDeleted) {
            return ResponseEntity.ok("Post deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post couldn't deleted");
        }
    }

}
