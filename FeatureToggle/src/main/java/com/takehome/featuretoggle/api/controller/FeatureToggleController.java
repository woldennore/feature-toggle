package com.takehome.featuretoggle.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.takehome.featuretoggle.api.request.CreateFeatureToggleRequest;
import com.takehome.featuretoggle.api.request.UpdateFeatureToggleRequest;
import com.takehome.featuretoggle.api.response.FeatureToggleResponse;
import com.takehome.featuretoggle.api.response.Response;
import com.takehome.featuretoggle.api.service.FeatureToggleService;

@RestController
@RequestMapping("/api/feature/")
@CrossOrigin(origins = "*")
public class FeatureToggleController {
	
	@Autowired
	FeatureToggleService featureToggleService;
	
	private static final Logger log = org.slf4j.LoggerFactory.getLogger(FeatureToggleController.class);
	
	@PostMapping
	public ResponseEntity<Response<FeatureToggleResponse>> create(@Valid @RequestBody CreateFeatureToggleRequest request, BindingResult result){
		Response<FeatureToggleResponse> response = new Response<FeatureToggleResponse>();
		if(result.hasErrors()) {
			log.error("Feature validation error: ", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		try {
			response.setData(this.featureToggleService.create(request));
		} catch (Exception e) {
			log.error("Feature creation error: ", e.getMessage());
			response.getErros().add(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Response<List<FeatureToggleResponse>>> findAll(){
		Response<List<FeatureToggleResponse>> response = new Response<List<FeatureToggleResponse>>();
		try {
			response.setData(featureToggleService.findAll());
		} catch (Exception e) {
			log.error("Feature listing error: ", e.getMessage());
			response.getErros().add(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PutMapping
	public ResponseEntity<Response<FeatureToggleResponse>> update(@Valid @RequestBody UpdateFeatureToggleRequest request, BindingResult result){
		Response<FeatureToggleResponse> response = new Response<FeatureToggleResponse>();
		if(result.hasErrors()) {
			log.error("Feature validation error: ", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErros().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		try {
			response.setData(this.featureToggleService.update(request));
		} catch (Exception e) {
			log.error("Feature update error: ", e.getMessage());
			response.getErros().add(e.getMessage());
			return ResponseEntity.internalServerError().body(response);
		}
		
		return ResponseEntity.ok(response);
	}
	
}
