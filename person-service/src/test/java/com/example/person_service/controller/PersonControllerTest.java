package com.example.person_service.controller;

import com.example.person_service.dao.PersonRepository;
import com.example.person_service.model.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonRepository personRepository;

    //Test 1: Get ALl:
    @Test
    public void testGetAllPersons() throws Exception {
        Person p1 = new Person(1L, "John", "Address", "3000", "25", "Job", "email", "123");

        // Simulate Mock (Database behaviour return only 1 person);
        Mockito.when(personRepository.findAll()).thenReturn(Arrays.asList(p1));

        mockMvc.perform(get("/persons/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John"));
    }

    // Test 2: Get 1 person by ID
    @Test
    public void testGetPersonById() throws Exception {
        Person p1 = new Person(1L, "John", "Address", "3000", "25", "Job", "email", "123");

        Mockito.when(personRepository.findById(1L)).thenReturn(Optional.of(p1));

        mockMvc.perform(get("/item/person/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }

    // Test 3: Create Person
    @Test
    public void testCreatePerson() throws Exception {
        Person p1 = new Person(1L, "John", "Address", "3000", "25", "Job", "email", "123");

        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(p1);

        String jsonContent = "{\"name\": \"John\", \"address\": \"Address\", \"postcode\": \"3000\", \"age\": \"25\", \"job\": \"Job\", \"email\": \"email\", \"phoneno\": \"123\"}";

        mockMvc.perform(post("/person")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"));
    }
}