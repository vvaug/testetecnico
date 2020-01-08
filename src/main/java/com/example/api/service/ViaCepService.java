package com.example.api.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.api.dto.DadosCep;

@Service
public class ViaCepService {

	private RestTemplate restTemplate = new RestTemplate();
	
	private final String uri = "http://viacep.com.br/ws/%s/json/";
	
	public DadosCep getDadosCep(String cep) {
		if ( hasError(cep) ) {
			
			return new DadosCep("erro", null, null, null, null, null, null, null, null);
		}
		
		return restTemplate.getForObject(String.format(uri, cep), DadosCep.class);
	}
	
	public boolean hasError(String cep) {
		
		String aux = String.format(uri, cep);
		
		String result = restTemplate.getForObject(aux, String.class);
		
		if ( result.contains("erro")) {
			
			return true;
		}
		
		return false;
	}
}
