package com.example.api;

import java.util.List;

import org.h2.mvstore.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiApplicationTests {

	@Autowired
	CustomerService customerService;
	
	RestTemplate rest = new RestTemplate();
	
	
	@Test
	public void find() {
		Customer customer = rest.getForObject("http://localhost:8080/customers/3", Customer.class);
		Assert.assertNotNull(customer);
	}

}
