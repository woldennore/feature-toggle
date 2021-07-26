package com.takehome.featuretoggle.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.takehome.featuretoggle.api.entity.Customer;
import com.takehome.featuretoggle.api.entity.FeatureToggle;
import com.takehome.featuretoggle.api.repository.FeatureToggleRepository;
import com.takehome.featuretoggle.api.request.CreateFeatureToggleRequest;
import com.takehome.featuretoggle.api.request.UpdateFeatureToggleRequest;
import com.takehome.featuretoggle.api.response.FeatureToggleResponse;

@Service
public class FeatureToggleService {
	
	@Autowired
	FeatureToggleRepository featureToggleRepository;
	
	@Autowired
	CustomerService customerService;

	public FeatureToggleResponse create(CreateFeatureToggleRequest request) throws NumberFormatException, Exception {		 
		FeatureToggle featureToggle = new FeatureToggle(request.getDisplayName(), request.getTechnicalName(), request.getExpiresOn(), request.getDescription(), request.getInverted(), null);
		
		if(!CollectionUtils.isEmpty(request.getCustomerIds())){
			Set<Customer> customers = new HashSet<Customer>();
			for (String customerId : request.getCustomerIds()) {
				Customer customer = customerService.findById(Long.valueOf(customerId));
				customers.add(customer);
			}
			featureToggle.setCustomers(customers);
		}
		featureToggle = featureToggleRepository.save(featureToggle);
		return new FeatureToggleResponse(featureToggle.getId(),
											featureToggle.getDisplayName(), 
											featureToggle.getTechnicalName(), 
											featureToggle.getExpiresOn(), 
											featureToggle.getDescription(), 
											featureToggle.getInverted(),
											featureToggle.getArchived(),
											featureToggle.getCustomersId());
	}

	public List<FeatureToggleResponse> findAll() {
		List<FeatureToggleResponse> response = new ArrayList<FeatureToggleResponse>();
		Iterable<FeatureToggle> result = featureToggleRepository.findAll();
		result.forEach(featureToggle -> {
			response.add(new FeatureToggleResponse(featureToggle.getId(),
													featureToggle.getDisplayName(), 
													featureToggle.getTechnicalName(), 
													featureToggle.getExpiresOn(), 
													featureToggle.getDescription(), 
													featureToggle.getInverted(), 
													featureToggle.getArchived(),
													featureToggle.getCustomersId()));
		});
		return response;
	}

	public FeatureToggleResponse update(UpdateFeatureToggleRequest request) throws Exception {
		FeatureToggle featureToggle = this.findById(request.getId());
		featureToggle.setDisplayName(request.getDisplayName());
		featureToggle.setTechnicalName(request.getTechnicalName());
		featureToggle.setExpiresOn(request.getExpiresOn());
		featureToggle.setDescription(request.getDescription());
		featureToggle.setInverted(request.getInverted());
		featureToggle.setArchived(request.getArchived());
		
		if(!CollectionUtils.isEmpty(request.getCustomerIds())){
			Set<Customer> customers = new HashSet<Customer>();
			for (String customerId : request.getCustomerIds()) {
				Customer customer = customerService.findById(Long.valueOf(customerId));
				customers.add(customer);
			}
			featureToggle.setCustomers(customers);
		}
		featureToggle = featureToggleRepository.save(featureToggle);
		return new FeatureToggleResponse(featureToggle.getId(),
											featureToggle.getDisplayName(), 
											featureToggle.getTechnicalName(), 
											featureToggle.getExpiresOn(), 
											featureToggle.getDescription(), 
											featureToggle.getInverted(), 
											featureToggle.getArchived(),
											featureToggle.getCustomersId());
	}
	
	public FeatureToggleResponse achive(Long id) throws Exception {
		FeatureToggle featureToggle = this.findById(id);
		featureToggle.setArchived(true);
		featureToggle.setExpiresOn(new Date());
		featureToggle = featureToggleRepository.save(featureToggle);
		return new FeatureToggleResponse(featureToggle.getId(),
				featureToggle.getDisplayName(), 
				featureToggle.getTechnicalName(), 
				featureToggle.getExpiresOn(), 
				featureToggle.getDescription(), 
				featureToggle.getInverted(), 
				featureToggle.getArchived(),
				featureToggle.getCustomersId());
		
	}
	
	public FeatureToggle findById(Long id) throws Exception {
		Optional<FeatureToggle> featureToggle = featureToggleRepository.findById(id);
		
		if(featureToggle.isEmpty()) {
			throw new Exception("FeatureToggle not found by ID: " + id);
		}
		return featureToggle.get();
	}
	
	public FeatureToggle findByTechnicalName(String technicalName) throws Exception {
		Optional<FeatureToggle> featureToggle = featureToggleRepository.findByTechnicalName(technicalName);
		
		if(featureToggle.isEmpty()) {
			throw new Exception("FeatureToggle not found by technicalName: " + technicalName);
		}
		return featureToggle.get();
	}
	
	

}
