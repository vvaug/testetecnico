package com.example.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/*
 * 7.)	Possibilite o cadastro de múltiplos endereços para um cliente.
 *  Como não existia nenhum modelo para Endereços, estou criando aqui.
 */
@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NotBlank
	@Column(nullable = false)
	private String streetName;
	@NotBlank
	@Column(nullable = false)
	private String zipCode;
	@NotBlank
	@Column(nullable = false)
	private String state;
	
	public Address() {
		
	}

	public Address(String streetName, String zipcode, String state) {
		super();
		this.streetName = streetName;
		this.zipCode = zipcode;
		this.state = state;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipcode(String zipcode) {
		this.zipCode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
