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
import org.springframework.web.bind.annotation.RestController;

import com.takehome.featuretoggle.api.request.FeatureCustomerRequest;
import com.takehome.featuretoggle.api.response.FeatureCustomerResponse;
import com.takehome.featuretoggle.api.response.Response;
import com.takehome.featuretoggle.api.service.FeatureCustomerService;

@RestController
@RequestMapping("/api/features/")
@CrossOrigin(origins = "*")
public class FeatureCustomerController {
	
	@Autowired
	FeatureCustomerService featureCustomerService;
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(FeatureCustomerController.class);
	
	@PostMapping
	public ResponseEntity<Response<List<FeatureCustomerResponse>>> create(@Valid @RequestBody FeatureCustomerRequest request, BindingResult result){
		Response<List<FeatureCustomerResponse>> response = new Response<List<FeatureCustomerResponse>>();
		if(result.hasErrors()) {
			log.error("Request validation error: ", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		try {
			response.setData(this.featureCustomerService.listFeatures(request));
		} catch (Exception e) {
			log.error("Feature listing error: ", e.getMessage());
			response.getErros().add(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
		
		return ResponseEntity.ok(response);
	}

}
