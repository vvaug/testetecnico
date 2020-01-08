package com.example.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Address;
import com.example.api.exception.InvalidCepException;
import com.example.api.repository.AddressRepository;

@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	public Address find(Long id) {
		return addressRepository.findById(id).orElse(null);
	}
	
	public List<Address> findAll(){
		return addressRepository.findAllByOrderByStreetNameAsc();
	}
	
	public void insert(Address address) throws InvalidCepException {
		if (! cepIsValido(address.getZipCode())) {
			throw new InvalidCepException("O Cep informado é invalido.");
		}
		
		addressRepository.save(address);
	}
	
	public void remove(Long id) {
		addressRepository.deleteById(id);
	}
	
	public boolean cepIsValido(String cep) {
		return cep.equals(new ViaCepService().getDadosCep(cep).getCep().replace("-", ""));
	}
	
}
