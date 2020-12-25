package com.udacity.jdnd.course3.critter.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table
//lombok is helpful in reducing the boilerplate code:
//@Data: setters, getters, constructor with all parameter
//@NoArgsConstructor: constructor without any parameter
@Data
@NoArgsConstructor
// any class that can be persisted has to implement Serializable
public class Customer implements Serializable {
    @Id
    // Indicates that the persistence provider must assign primary keys for the entity using a database identity column.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String phoneNumber;

    private String notes;

    @OneToMany(mappedBy = "customer")
    private Set<Pet> pets;

    public void insertPet(Pet pet){
        pets.add(pet);
    }
}
