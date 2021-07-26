package com.takehome.featuretoggle.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takehome.featuretoggle.api.entity.Customer;
import com.takehome.featuretoggle.api.repository.CustomerRepository;
import com.takehome.featuretoggle.api.request.CreateCustomerRequest;
import com.takehome.featuretoggle.api.response.CustomerResponse;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public Customer findById(Long id) throws Exception {
		Optional<Customer> customer = customerRepository.findById(id);

		if (customer.isEmpty()) {
			throw new Exception("Customer not found by ID: " + id);
		}
		return customer.get();
	}

	public Boolean existsById(Long id) {
		return customerRepository.existsById(id);
	}

	public CustomerResponse create(CreateCustomerRequest request) {
		Customer customer = new Customer(request.getName());
		customer = customerRepository.save(customer);
		return new CustomerResponse(customer.getId().toString(), customer.getName());
	}

	public List<CustomerResponse> findAll() {
		Iterable<Customer> result = customerRepository.findAll();

		List<CustomerResponse> response = new ArrayList<CustomerResponse>();
		result.forEach(customer -> {
			response.add(new CustomerResponse(customer.getId().toString(), customer.getName()));
		});

		return response;
	}
}
