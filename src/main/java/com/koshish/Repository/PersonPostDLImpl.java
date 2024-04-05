package com.koshish.Repository;

import com.koshish.Model.PersonPost;
import com.koshish.Model.PostWithComments;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonPostDLImpl {

    DB db = new DB();

    public List<PersonPost> getAllPeoplePost() throws SQLException {
        List<PersonPost> personPostList = new ArrayList<>();

        String selectQuery = "SELECT * FROM PERSON_POST";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = db.connection.prepareStatement(selectQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PersonPost personPost = new PersonPost();
                personPost.setPersonPostId(resultSet.getInt("personPostId"));
                personPost.setUserId(resultSet.getInt("userId"));
                personPost.setUserName(resultSet.getString("userName"));
                personPost.setPost(resultSet.getString("post"));
                personPostList.add(personPost);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return personPostList;
    }

    public PersonPost addPersonPost(PersonPost personPost) throws SQLException {
        String insertQuery = "INSERT INTO PERSON_POST (userId, userName, post) values "
                + "(?, ?, ?)";
        PreparedStatement preparedStatement = db.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, personPost.getUserId());
        preparedStatement.setString(2, personPost.getUserName());
        preparedStatement.setString(3, personPost.getPost());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Inserting person post failed, no rows affected.");
        }

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                personPost.setPersonPostId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Inserting person post failed, no ID obtained.");
            }
        }

        return personPost;
    }

    public PersonPost getPostByPersonPostId(int personPostId) throws SQLException {
        String query = "SELECT * FROM PERSON_POST WHERE personPostId = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        PersonPost personPost = new PersonPost();

        preparedStatement = db.connection.prepareStatement(query);
        preparedStatement.setInt(1, personPostId);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            personPost.setPersonPostId(resultSet.getInt("personPostId"));
            personPost.setUserId(resultSet.getInt("userId"));
            personPost.setUserName(resultSet.getString("userName"));
            personPost.setPost(resultSet.getString("post"));
        }
        preparedStatement.close();
        resultSet.close();
        return personPost;
    }

    public PersonPost editPost(PersonPost personPost) throws SQLException {
        String query = "UPDATE PERSON_POST SET post = ? WHERE personPostId = ?";

        PreparedStatement preparedStatement = null;
        preparedStatement = db.connection.prepareStatement(query);
        preparedStatement.setString(1, personPost.getPost());
        preparedStatement.setInt(2, personPost.getPersonPostId());

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            return personPost;
        }
        return null;
    }

    /**
     *  TODO: Debug through this and optimize it
     */
    public PostWithComments getPostWithComments(int personPostId) throws SQLException {
        String query = "SELECT pp.userId, pp.personPostId, pc.personCommentId, pp.userName, pp.post, pc.comment " +
                "FROM PERSON_POST pp " +
                "LEFT JOIN PERSON_COMMENT pc ON pp.personPostId = pc.personPostId " +
                "WHERE pp.personPostId = ?";
        PreparedStatement preparedStatement = db.connection.prepareStatement(query);
        preparedStatement.setInt(1, personPostId);
        ResultSet resultSet = preparedStatement.executeQuery();

        Map<Integer, PostWithComments> postMap = new HashMap<>();

        PostWithComments postWithComments = new PostWithComments();
        boolean postChecked = false;
        while (resultSet.next()) {
            if (!postChecked) {
            int userId = resultSet.getInt("userId");
            int currentPersonPostId = resultSet.getInt("personPostId");
            int personCommentId = resultSet.getInt("personCommentId");
            String userName = resultSet.getString("userName");
            String post = resultSet.getString("post");

            postWithComments.setUserId(userId);
            postWithComments.setPersonPostId(currentPersonPostId);
            postWithComments.setPersonCommentId(personCommentId);
            postWithComments.setUserName(userName);
            postWithComments.setPost(post);

            postChecked = true;
            }

            String comment = resultSet.getString("comment");
            if (comment != null) {
                if (postWithComments.getComment() == null) {
                    postWithComments.setComment(new ArrayList<>());
                }
                postWithComments.getComment().add(comment);
            }
        }
        return postWithComments;
    }

    public List<PostWithComments> getPostsWithCommentByUserId(int userId) throws SQLException {
        List<PostWithComments> postWithCommentsList;
        String query = "SELECT pp.userId, pp.personPostId, pp.userName AS postUserName, pp.post, pc.personCommentId, pc.userName AS commentUserName, pc.comment " +
                "FROM PERSON_POST pp " +
                "LEFT JOIN PERSON_COMMENT pc ON pp.personPostId = pc.personPostId " +
                "WHERE pp.userId = ?";
        PreparedStatement preparedStatement = db.connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        Map<Integer, PostWithComments> map = new HashMap<>();
        while (resultSet.next()) {
            int personPostId = resultSet.getInt("personPostId");
            if (!map.containsKey(personPostId)) {
                PostWithComments postWithComments = new PostWithComments();
                postWithComments.setPersonPostId(personPostId);
                postWithComments.setUserId(resultSet.getInt("userId"));
                postWithComments.setPersonCommentId(resultSet.getInt("personCommentId"));
                postWithComments.setUserName(resultSet.getString("postUserName"));
                postWithComments.setPost(resultSet.getString("post"));
                map.put(personPostId, postWithComments);
            }
            String comment = resultSet.getString("comment");
            if (comment != null) {
                PostWithComments currentPostWithComments = map.get(personPostId);
                if (currentPostWithComments != null && currentPostWithComments.getComment() == null) {
                    currentPostWithComments.setComment(new ArrayList<>());
                }
                // Following doesn't work, don't do this
//                if (map.get(personPostId).getComment() == null) {
//                    map.get(personPostId).setComment(new ArrayList<>());
//                }
                map.get(personPostId).getComment().add(comment);
            }
        }
        postWithCommentsList = new ArrayList<>(map.values());
        return postWithCommentsList;
    }

    public List<PostWithComments> getPostsWithComments() throws SQLException {
        List<PostWithComments> postWithCommentsList;
        String query = "SELECT pp.userId, pp.personPostId, pp.userName AS postUserName, pp.post, pc.personCommentId, pc.userName AS commentUserName, pc.comment " +
                "FROM PERSON_POST pp " +
                "LEFT JOIN PERSON_COMMENT pc ON pp.personPostId = pc.personPostId ";
        PreparedStatement preparedStatement = db.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        Map<Integer, PostWithComments> map = new HashMap<>();
        while (resultSet.next()) {
            int personPostId = resultSet.getInt("personPostId");
            if (!map.containsKey(personPostId)) {
                PostWithComments postWithComments = new PostWithComments();
                postWithComments.setPersonPostId(personPostId);
                postWithComments.setUserId(resultSet.getInt("userId"));
                postWithComments.setPersonCommentId(resultSet.getInt("personCommentId"));
                postWithComments.setUserName(resultSet.getString("postUserName"));
                postWithComments.setPost(resultSet.getString("post"));
                map.put(personPostId, postWithComments);
            }
            String comment = resultSet.getString("comment");
            if (comment != null) {
                PostWithComments currentPostWithComments = map.get(personPostId);
                if (currentPostWithComments != null && currentPostWithComments.getComment() == null) {
                    currentPostWithComments.setComment(new ArrayList<>());
                }
                map.get(personPostId).getComment().add(comment);
            }
        }
        postWithCommentsList = new ArrayList<>(map.values());
        return postWithCommentsList;
    }

    public boolean deletePostByPersonPostId(int personPostId) throws SQLException {
        String query = "DELETE FROM KoKo_DBOne.PERSON_POST WHERE personPostId = ?";
        PreparedStatement preparedStatement = db.connection.prepareStatement(query);
        preparedStatement.setInt(1, personPostId);
        int rowAffected = preparedStatement.executeUpdate();
        return rowAffected > 0;
    }
}
