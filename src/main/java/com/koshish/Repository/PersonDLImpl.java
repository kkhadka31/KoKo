package com.koshish.Repository;

import com.koshish.Model.Person;
import com.koshish.Model.Role;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDLImpl {

    DB db = new DB();

    public List<Person> getPersons() throws SQLException {
        String query = "SELECT * FROM PERSON";
        List<Person> personList = new ArrayList<>();
        PreparedStatement preparedStatement = db.connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = new Person();
            person.setId(resultSet.getInt("userId"));
            person.setFirstName(resultSet.getString("firstName"));
            person.setSecondName(resultSet.getString("secondName"));
            person.setFullName(resultSet.getString("fullName"));
            person.setEmail(resultSet.getString("email"));
            person.setGender(resultSet.getString("gender"));
            person.setPhoneNumber(resultSet.getString("phoneNumber"));
            person.setUserName(resultSet.getString("userName"));
            person.setRole(Role.valueOf(resultSet.getString("role")));
            personList.add(person);
        }
        return personList;
    }

    public Person getPersonById(int id) {
        String selectQuery = "SELECT * from PERSON WHERE userId = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Person person = new Person();
        try {
            preparedStatement = db.connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                person.setId(resultSet.getInt("userId"));
                person.setFirstName(resultSet.getString("firstName"));
                person.setSecondName(resultSet.getString("secondName"));
                person.setFullName(resultSet.getString("fullName"));
                person.setEmail(resultSet.getString("email"));
                person.setGender(resultSet.getString("gender"));
                person.setPhoneNumber(resultSet.getString("phoneNumber"));
                person.setUserName(resultSet.getString("userName"));
                person.setRole(Role.valueOf(resultSet.getString("role")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return person;
    }



    public Person getPersonByUserName(String userName) {
        String selectQuery = "SELECT * from PERSON WHERE username = ?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Person person = new Person();
        try {
            preparedStatement = db.connection.prepareStatement(selectQuery);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                person.setId(resultSet.getInt("userId"));
                person.setFirstName(resultSet.getString("firstName"));
                person.setSecondName(resultSet.getString("secondName"));
                person.setFullName(resultSet.getString("fullName"));
                person.setEmail(resultSet.getString("email"));
                person.setGender(resultSet.getString("gender"));
                person.setPhoneNumber(resultSet.getString("phoneNumber"));
                person.setUserName(resultSet.getString("userName"));
                person.setPassword(resultSet.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return person;
    }

//    public Person addPerson(Person person) throws SQLException {
//        String query = "INSERT INTO PERSON (id, firstName, secondName, fullName, email, gender, phoneNumber, password, username) values "
//                + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement preparedStatement = db.connection.prepareStatement(query);
//        preparedStatement.setLong(1, person.getId());
//        preparedStatement.setString(2, person.getFirstName());
//        preparedStatement.setString(3, person.getSecondName());
//        preparedStatement.setString(4, person.getFirstName());
//        preparedStatement.setString(5, person.getEmail());
//        preparedStatement.setString(6, person.getGender());
//        preparedStatement.setString(7, person.getPhoneNumber());
//        preparedStatement.setString(8, person.getPassword());
//        preparedStatement.setString(9, person.getUsername());
//        preparedStatement.execute();
//        preparedStatement.close();
//        return person;

//    }
}
