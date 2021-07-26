package com.takehome.featuretoggle.api.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class FeatureCustomerRequest {

	@NotBlank(message = "technicalName can not be blank")
	private String customerId;
	private List<FeatureNameRequest> features;
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	@NotEmpty(message = "features can not be empty")
	public List<FeatureNameRequest> getFeatures() {
		return features;
	}
	public void setFeatures(List<FeatureNameRequest> features) {
		this.features = features;
	}
	
}
