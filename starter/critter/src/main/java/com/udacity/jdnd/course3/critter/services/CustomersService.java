package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.entities.Customer;
import com.udacity.jdnd.course3.critter.entities.Pet;
import com.udacity.jdnd.course3.critter.repositories.CustomerRepository;
import com.udacity.jdnd.course3.critter.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomersService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerByPetId(Long PetId){
        return petRepository.getOne(PetId).getCustomer();
    }

    public Customer saveCustomer(Customer customer, List<Long>petIds){
        Set<Pet> pets = new HashSet<>();
        if (petIds != null && !petIds.isEmpty()){
            pets = petIds.stream().map(petId -> petRepository.getOne(petId)).collect(Collectors.toSet());
        }
        customer.setPets(pets);
        return customerRepository.save(customer);
    }
}
