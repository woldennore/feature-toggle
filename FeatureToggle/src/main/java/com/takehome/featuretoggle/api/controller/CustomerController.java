package com.takehome.featuretoggle.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.takehome.featuretoggle.api.request.CreateCustomerRequest;
import com.takehome.featuretoggle.api.response.CustomerResponse;
import com.takehome.featuretoggle.api.response.Response;
import com.takehome.featuretoggle.api.service.CustomerService;

@RestController
@RequestMapping("/api/customer/")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<Response<CustomerResponse>> create(@Valid @RequestBody CreateCustomerRequest request, BindingResult result){
		Response<CustomerResponse> response = new Response<CustomerResponse>();
		if(result.hasErrors()) {
			log.error("Customer validation error: ", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		try {
			response.setData(this.customerService.create(request));
		} catch (Exception e) {
			log.error("Customer creation error: ", e.getMessage());
			response.getErros().add(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Response<List<CustomerResponse>>> findAll(){
		Response<List<CustomerResponse>> response = new Response<List<CustomerResponse>>();
		try {
			response.setData(customerService.findAll());
		} catch (Exception e) {
			log.error("Feature listing error: ", e.getMessage());
			response.getErros().add(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
		return ResponseEntity.ok(response);
	}

}
