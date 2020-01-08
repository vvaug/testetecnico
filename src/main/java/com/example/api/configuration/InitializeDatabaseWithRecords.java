package com.example.api.configuration;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.api.domain.Address;
import com.example.api.domain.Customer;
import com.example.api.domain.Perfil;
import com.example.api.domain.Usuario;
import com.example.api.repository.PerfilRepository;
import com.example.api.repository.UsuarioRepository;
import com.example.api.service.AddressService;
import com.example.api.service.CustomerService;

@Component
public class InitializeDatabaseWithRecords implements CommandLineRunner {
	 
	@Autowired
	CustomerService costumerService;
	@Autowired
	AddressService addressService;
	@Autowired
	UsuarioRepository usuarioRepository;
	@Autowired
	PerfilRepository perfilRepository;
	
    @Override
    public void run(String...args) throws Exception {
    	
    	Address address = new Address("Rua Arlindo", "09811323", "SP");
    	Address address2 = new Address("Av Pres Joao Cafe Filho", "09852140", "SP");
    	
        Customer customer = new Customer("Victor", "victor@sysmap.com.br");
        
        List<Address> addresses = new ArrayList<>();
        
        addresses.add(address);
        addresses.add(address2);
        
        customer.setAddress(addresses);
        
        addressService.insert(address);
        addressService.insert(address2);
        
        costumerService.insert(customer);
        
        Usuario usuario = new Usuario();
        Perfil perfil = new Perfil();
        
        perfil.setNome("ADMIN");
        usuario.setEmail("victorhyuuk1@sysmap.com.br");
        usuario.setSenha("$2a$10$Uo1LG4Qlo79dC3DuysVFDO/7h2VJQ184vuwTx6ptZBgo1lms1FkG2");
        usuario.setNome("Victor");
        usuario.setPerfis(Arrays.asList(perfil));
        
        perfilRepository.save(perfil);
        usuarioRepository.save(usuario);
        
    }
}