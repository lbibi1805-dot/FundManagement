package com.example.person_service.controller;

import com.example.person_service.dao.PersonRepository;
import com.example.person_service.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository repository;

    // create
    @PostMapping("/person")
    public Person addPerson(@RequestBody Person person){
        return repository.save(person);
    }

    // Get One
    @GetMapping("/item/person/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id){
        Optional<Person> person = repository.findById(id);
        return person.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update:
    @PutMapping("/persons/person")
    public Person updatePerson(@RequestBody Person person){
        return repository.save(person);
    }

    // Delete
    @DeleteMapping("/persons/person")
    public void deletePerson(@RequestBody Person person){
        repository.delete(person);
    }
}
