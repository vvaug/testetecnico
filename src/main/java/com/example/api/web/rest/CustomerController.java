package com.example.api.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.dto.CustomResponse;
import com.example.api.exception.AddressNotFoundException;
import com.example.api.exception.CostumerNotFoundException;
import com.example.api.exception.InvalidCepException;
import com.example.api.service.AddressService;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private CustomerService cservice;
	private AddressService  aservice;

	@Autowired
	public CustomerController(CustomerService cservice, AddressService aservice) {
		this.cservice = cservice;
		this.aservice = aservice;
	}

	@GetMapping
	public Page<Customer> findAll(@PageableDefault(sort = "id",
	                                               direction = Direction.ASC,
	                                               size = 10)
	                              Pageable pageable) {
		return cservice.findAll(pageable);
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) throws CostumerNotFoundException {
		return cservice.findById(id);
	}
	
	@PostMapping
	public ResponseEntity<Customer> insert(@Valid @RequestBody Customer customer) {
		cservice.insert(customer);
	    return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomResponse> remove(@PathVariable Long id){
		cservice.remove(id);		
		CustomResponse customResponse = new CustomResponse("Removido", HttpStatus.OK.toString());
		return new ResponseEntity<>(customResponse, HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@Valid @RequestBody Customer customer,
			                                      @PathVariable Long id) throws CostumerNotFoundException{
		Customer oldCustomer = cservice.findById(id);
		oldCustomer.setEmail(customer.getEmail());
		oldCustomer.setName(customer.getName());
		cservice.insert(oldCustomer);
		return new ResponseEntity<>(oldCustomer, HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}/addresses")
	public ResponseEntity<List<Address>> showAllAddressByCostumer(@PathVariable Long id) throws CostumerNotFoundException{
		List<Address> addresses = new ArrayList<>();
		Customer customer = cservice.findById(id);
		addresses = customer.getAddress();
		return new ResponseEntity<>(addresses, HttpStatus.OK);
	}
	
	@PostMapping("/{id}/addresses")
	public ResponseEntity<Customer> insertAddress(@Valid @PathVariable Long id, @RequestBody Address address) throws InvalidCepException, CostumerNotFoundException{
		Customer customer = cservice.findById(id);
		aservice.insert(address);
		customer.getAddress().add(address);
		cservice.insert(customer);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}/addresses/{adId}")
	public ResponseEntity<CustomResponse> removeAddress(@PathVariable("id") Long id, 
			                                            @PathVariable("adId") Long adId) throws CostumerNotFoundException, AddressNotFoundException{
		int aux = 0;
		
		Customer customer = cservice.findById(id);
		CustomResponse customResponse = new CustomResponse("Removido", HttpStatus.OK.toString());
		
		for ( Address a : customer.getAddress()) {
			if ( a.getId() == adId ) {
				aservice.remove(adId);
				aux = ( aux + 1);
			}
		}
		
		if ( aux == 0 ) {
			
			throw new AddressNotFoundException("Endereco nao encontrado para o cliente informado!");
		}
		return new ResponseEntity<>(customResponse, HttpStatus.OK);
	}
}
