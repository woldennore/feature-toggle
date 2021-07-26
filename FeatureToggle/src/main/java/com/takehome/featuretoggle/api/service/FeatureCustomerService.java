package com.takehome.featuretoggle.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.takehome.featuretoggle.api.entity.FeatureToggle;
import com.takehome.featuretoggle.api.request.FeatureCustomerRequest;
import com.takehome.featuretoggle.api.request.FeatureNameRequest;
import com.takehome.featuretoggle.api.response.FeatureCustomerResponse;

@Service
public class FeatureCustomerService {
	
	@Autowired
	FeatureToggleService featureToggleService;
	
	@Autowired
	CustomerService customerService;

	public List<FeatureCustomerResponse> listFeatures(FeatureCustomerRequest request) throws NumberFormatException, Exception {
		if(!customerService.existsById(Long.valueOf(request.getCustomerId()))) {
			throw new Exception("Customer not found by ID: " + request.getCustomerId());
		}
		List<FeatureToggle> features = new ArrayList<FeatureToggle>();
		
		List<String> technicalNames = request.getFeatures().stream().map(FeatureNameRequest::getName).collect(Collectors.toList());
		for (String string : technicalNames) {
			features.add(featureToggleService.findByTechnicalName(string));
		}
		
		return createFeatureCustomerResponse(request.getCustomerId(), features);
	}

	
	private List<FeatureCustomerResponse> createFeatureCustomerResponse(String customerId, List<FeatureToggle> features){
		List<FeatureCustomerResponse> response = new ArrayList<FeatureCustomerResponse>();
		for (FeatureToggle featureToggle : features) {
			FeatureCustomerResponse featureResponse = new FeatureCustomerResponse(featureToggle.getTechnicalName(),
																				  featureToggle.getCustomersId().contains(customerId) && !featureToggle.getArchived(),
																				  featureToggle.getInverted(),
																				  featureToggle.isExpired());
			response.add(featureResponse);
		}
		return response;
		
	}
}
