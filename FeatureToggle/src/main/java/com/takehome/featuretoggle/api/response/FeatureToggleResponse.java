package com.takehome.featuretoggle.api.response;

import java.util.Date;
import java.util.List;

public class FeatureToggleResponse {

	private Long id; 
	private String displayName;
	private String technicalName;
	private Date expiresOn;
	private String description;
	private Boolean inverted;
	private Boolean archived;
	private List<String> customerIds;
	
	
	public FeatureToggleResponse(Long id, String displayName, String technicalName, Date expiresOn, String description,
			Boolean inverted,Boolean archived, List<String> customerIds) {
		super();
		this.id = id;
		this.displayName = displayName;
		this.technicalName = technicalName;
		this.expiresOn = expiresOn;
		this.description = description;
		this.inverted = inverted;
		this.archived = archived;
		this.customerIds = customerIds;
	}
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
