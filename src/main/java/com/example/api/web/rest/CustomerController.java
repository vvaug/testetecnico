package com.example.api.web.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.exception.AddressNotFoundException;
import com.example.api.exception.CostumerNotFoundException;
import com.example.api.exception.InvalidCepException;
import com.example.api.service.AddressService;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AddressService  addressService;

	@GetMapping
	public Page<Customer> findAll(@PageableDefault(sort = "id",
	                                               direction = Direction.ASC,
	                                               size = 10)
	                              Pageable pageable) {
		return customerService.findAll(pageable);
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) throws CostumerNotFoundException {
		return customerService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer insert(@Valid @RequestBody Customer customer) {
		return customerService.save(customer);
	}
	
	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id){
		customerService.remove(id);		
	}
	
	@PutMapping("/{id}")
	public Customer update(@Valid @RequestBody Customer customer,
			                                      @PathVariable Long id) throws CostumerNotFoundException{
		Customer oldCustomer = customerService.findById(id);
		oldCustomer.setEmail(customer.getEmail());
		oldCustomer.setName(customer.getName());
		return customerService.save(oldCustomer);
		
	}
	
	@GetMapping("/{id}/addresses")
	public List<Address> showAllAddressByCostumer(@PathVariable Long id) throws CostumerNotFoundException{
		
		Customer customer = customerService.findById(id);
		
		return customer.getAddress();
	}
	
	@PostMapping("/{id}/addresses")
	public Customer insertAddress(@Valid @PathVariable Long id, @RequestBody Address address) throws InvalidCepException, CostumerNotFoundException{
	
		Customer customer = customerService.findById(id);
		
		addressService.insert(address);
		
		customer.getAddress().add(address);
		
		return customerService.save(customer);
	}
	
	@DeleteMapping("/{id}/addresses/{adressId}")
	public void removeAddress(@PathVariable("id") Long id, 
			                  @PathVariable("adressId") Long adressId) throws CostumerNotFoundException, AddressNotFoundException{
		
		Customer customer = customerService.findById(id);
		
		Address address = customer.getAddress().stream().filter(a -> a.getId().equals(adressId)).findFirst()
			.orElseThrow(() -> new AddressNotFoundException("Endereco nao encontrado para o cliente informado!"));
		
		addressService.remove(address.getId());
	}

}
