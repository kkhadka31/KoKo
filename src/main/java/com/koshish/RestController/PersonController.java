package com.koshish.RestController;

import com.koshish.Model.Person;
import com.koshish.Repository.PersonDLImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/rc")
public class PersonController {

    private PersonDLImpl personDL = new PersonDLImpl();

    @GetMapping("/persons")
    public ResponseEntity<List<Person>> getPersons() throws SQLException {
        List<Person> personList = personDL.getPersons();
        if (personList != null) {
            return ResponseEntity.ok(personList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<Person> getPersonById(@PathVariable int personId) {
        Person person = personDL.getPersonById(personId);
        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
