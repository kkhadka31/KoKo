package com.koshish.Repository;

import com.koshish.Model.PersonComment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PersonCommentDLImpl {

    DB db = new DB();

    public List<PersonComment> getAllPeopleComment() throws SQLException {
        List<PersonComment> personCommentList = new ArrayList<>();

        String selectQuery = "SELECT * FROM PERSON_COMMENT";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = db.connection.prepareStatement(selectQuery);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PersonComment personComment = new PersonComment();
                personComment.setPersonCommentId(resultSet.getInt("personCommentId"));;
                personComment.setPersonPostId(resultSet.getInt("personPostId"));
                personComment.setUserId(resultSet.getInt("userId"));
                personComment.setUserName(resultSet.getString("userName"));
                personComment.setComment(resultSet.getString("comment"));
                personCommentList.add(personComment);
            }
        } catch (SQLException e) {
            throw new SQLException(e);
        }
        return personCommentList;
    }

    public PersonComment addComment(PersonComment personComment) throws SQLException {
        String query = "INSERT INTO PERSON_COMMENT (personPostId, userId, userName, comment) " +
                "VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = db.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, personComment.getPersonPostId());
        preparedStatement.setInt(2, personComment.getUserId());
        preparedStatement.setString(3, personComment.getUserName());
        preparedStatement.setString(4, personComment.getComment());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows == 0) {
            throw new SQLException("Inserting person comment failed");
        }

        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                personComment.setPersonCommentId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Inserting person comment failed");
            }
        }

        return personComment;
    }

    public List<PersonComment> getCommentsByPersonPostId(int personPostId) throws SQLException {
        String query = "SELECT * from PERSON_COMMENT WHERE personPostId = ?";
        List<PersonComment> personCommentList = new ArrayList<>();
        PreparedStatement preparedStatement = db.connection.prepareStatement(query);
        preparedStatement.setInt(1, personPostId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            PersonComment personComment = new PersonComment();
            personComment.setPersonPostId(resultSet.getInt("personPostId"));
            personComment.setPersonCommentId(resultSet.getInt("personCommentId"));
            personComment.setUserId(resultSet.getInt("userId"));
            personComment.setUserName(resultSet.getString("userName"));
            personComment.setComment(resultSet.getString("comment"));
            personCommentList.add(personComment);
        }

        return personCommentList;
    }

    public List<PersonComment> getCommentsByUserId(int userId) throws SQLException {
        String query = "SELECT * from PERSON_COMMENT WHERE userId = ?";
        List<PersonComment> personCommentList = new ArrayList<>();
        PreparedStatement preparedStatement = db.connection.prepareStatement(query);
        preparedStatement.setInt(1, userId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            PersonComment personComment = new PersonComment();
            personComment.setPersonPostId(resultSet.getInt("personPostId"));
            personComment.setPersonCommentId(resultSet.getInt("personCommentId"));
            personComment.setUserId(resultSet.getInt("userId"));
            personComment.setUserName(resultSet.getString("userName"));
            personComment.setComment(resultSet.getString("comment"));
            personCommentList.add(personComment);
        }

        return personCommentList;
    }

    public PersonComment getCommentByPersonCommentId(int personCommentId) throws SQLException {
        String query = "SELECT * from PERSON_COMMENT WHERE personCommentId = ?";
        PersonComment personComment = new PersonComment();
        PreparedStatement preparedStatement = db.connection.prepareStatement(query);
        preparedStatement.setInt(1, personCommentId);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            personComment.setPersonPostId(resultSet.getInt("personPostId"));
            personComment.setPersonCommentId(resultSet.getInt("personCommentId"));
            personComment.setUserId(resultSet.getInt("userId"));
            personComment.setUserName(resultSet.getString("userName"));
            personComment.setComment(resultSet.getString("comment"));
        }

        return personComment;
    }

    public PersonComment editCommentByPersonCommentId(PersonComment personComment) throws SQLException {
        String query = "UPDATE PERSON_COMMENT SET comment = ? WHERE personCommentId = ?";

        PreparedStatement preparedStatement = null;
        preparedStatement = db.connection.prepareStatement(query);
        preparedStatement.setString(1, personComment.getComment());
        preparedStatement.setInt(2, personComment.getPersonCommentId());

        int rowsUpdated = preparedStatement.executeUpdate();
        if (rowsUpdated > 0) {
            return personComment;
        }
        return null;
    }

    public boolean deleteCommentByPersonCommentId(int personCommentId) throws SQLException {
        String query = "DELETE FROM KoKo_DBOne.PERSON_COMMENT WHERE personCommentId = ?";
        PreparedStatement preparedStatement = db.connection.prepareStatement(query);
        preparedStatement.setInt(1, personCommentId);
        int rowAffected = preparedStatement.executeUpdate();
        return rowAffected > 0;
    }
}
