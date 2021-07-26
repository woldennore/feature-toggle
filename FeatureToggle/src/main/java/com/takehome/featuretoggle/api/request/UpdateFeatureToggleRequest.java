package com.takehome.featuretoggle.api.request;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateFeatureToggleRequest {

	private Long id; 
	private String displayName;
	private String technicalName;
	private Date expiresOn;
	private String description;
	private Boolean inverted;
	private Boolean archived;
	private List<String> customerIds;
	
	@NotNull(message = "Id can not be null")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	@NotBlank(message = "technicalName can not be blank")
	public String getTechnicalName() {
		return technicalName;
	}
	public void setTechnicalName(String technicalName) {
		this.technicalName = technicalName;
	}
	public Date getExpiresOn() {
		return expiresOn;
	}
	public void setExpiresOn(Date expiresOn) {
		this.expiresOn = expiresOn;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@NotNull(message = "inverted can not be null")
	public Boolean getInverted() {
		return inverted;
	}
	public void setInverted(Boolean inverted) {
		this.inverted = inverted;
	}
	public List<String> getCustomerIds() {
		return customerIds;
	}
	public void setCustomerIds(List<String> customerIds) {
		this.customerIds = customerIds;
	}
	public Boolean getArchived() {
		return archived;
	}
	public void setArchived(Boolean archived) {
		this.archived = archived;
	}
}
