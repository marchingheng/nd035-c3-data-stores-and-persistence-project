package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

public class PetsService {
    @Autowired
    PetRepository petRepository;

    @Autowired
    CustomerRepository customerRepository;

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public Set<Pet> getPetsByCustomerId(long customerId){
        return customerRepository.getOne(customerId).getPets();
    }

    public Pet getPetById(long petId){
        return petRepository.getOne(petId);
    }

    public Pet savePet(Pet pet, long owner_id){
        Customer owner = customerRepository.getOne(owner_id);
        pet.setCustomer(owner);
        pet = petRepository.save(pet);
        owner.insertPet(pet);
        customerRepository.save(owner);
        return pet;
    }
}
