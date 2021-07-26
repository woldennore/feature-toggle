package com.takehome.featuretoggle.api.request;

import javax.validation.constraints.NotBlank;

public class FeatureNameRequest {

	private String name;

	@NotBlank(message = "technicalName can not be blank")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
