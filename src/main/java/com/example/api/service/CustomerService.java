package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.exception.CostumerNotFoundException;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	public Page<Customer> findAll(Pageable pageable) {
		return repository.findAllByOrderByNameAsc(pageable);
	}

	public Customer findById(Long id) throws CostumerNotFoundException {
		return repository.findById(id).orElseThrow(() -> new CostumerNotFoundException("Cliente nao encontrado na base dados!"));
	}
	
	public Customer save(Customer customer) {
		return repository.save(customer);
	}
	
	public void remove(Long id) {
		repository.deleteById(id);
	}
	
	
}
