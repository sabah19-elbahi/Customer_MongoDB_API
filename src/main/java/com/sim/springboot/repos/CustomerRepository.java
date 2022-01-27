package com.sim.springboot.repos;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.sim.springboot.models.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
