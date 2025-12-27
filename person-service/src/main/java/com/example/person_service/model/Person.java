package com.example.person_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "person")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @JsonProperty("postcode")
    private String postCode;
    private String age;
    private String job;
    private String email;
    private String phoneno;

    public Person(String phoneno, String email, String job, String age, String postCode, String address, String name, Long id) {
        this.phoneno = phoneno;
        this.email = email;
        this.job = job;
        this.age = age;
        this.postCode = postCode;
        this.address = address;
        this.name = name;
        this.id = id;
    }
}
