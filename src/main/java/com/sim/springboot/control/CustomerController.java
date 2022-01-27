package com.sim.springboot.control;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sim.springboot.models.Customer;
import com.sim.springboot.repos.CustomerRepository;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerRepository customerRepo;
	
	@PostMapping("/customers/add")
	@Transactional
	@CrossOrigin
	public String saveCustomer(@ModelAttribute Customer customer) {
		customerRepo.save(customer);
		customer.getId();
		return "Saved customer with id : "+customer.getId();
	}
	
	@PutMapping("/customers/update/{id}")
	@Transactional
	@CrossOrigin
	public String saveCustomer(@ModelAttribute Customer customer,@PathVariable String id) {
		Optional<Customer> cs = customerRepo.findById(id);
		
		if (cs.isPresent()) {
		      Customer _customer = cs.get();
		      _customer.setGender(customer.getGender());
		      _customer.setAge(customer.getAge());
		      _customer.setIncome(customer.getIncome());
		      _customer.setScore(customer.getScore());
		      _customer.setId(id);
		      customerRepo.save(_customer);
		      return "Updated customer with id : "+customer.getId();
		    } else {
		    	return "Customer with id : "+customer.getId()+" Not found ! ";
		    }
		
	}
	
	@GetMapping("/customers")
	@Transactional
	@CrossOrigin
	public List<Customer> getAll(){
		return customerRepo.findAll();
	}
	
	@GetMapping("/customers/{id}")
	@Transactional
	@CrossOrigin
	public Optional<Customer> getCustomer(@PathVariable String id){
		return customerRepo.findById(id);
	}
	
	@DeleteMapping("/customers/delete/{id}")
	@Transactional
	@CrossOrigin
	public String deleteCustomer(@PathVariable String id) {
		customerRepo.deleteById(id);
		return "Saved customer with id : "+id;
	}
	
	
}
