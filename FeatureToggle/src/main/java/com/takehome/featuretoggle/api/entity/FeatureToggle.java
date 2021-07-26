package com.takehome.featuretoggle.api.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.util.CollectionUtils;

@Entity
@Table(name = "feature_toggle")
public class FeatureToggle {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(name = "display_name")
	private String displayName;
    @Column(name = "technical_name")
	private String technicalName;
    @Column(name = "expires_on")
	private Date expiresOn;
	private String description;
	private Boolean inverted;
	private Boolean archived;
	
    @ManyToMany
    @JoinTable(
      name = "feature_customer", 
      joinColumns = @JoinColumn(name = "feature_id"), 
      inverseJoinColumns = @JoinColumn(name = "customer_id"))
	private Set<Customer> customers;
    
	public FeatureToggle(String displayName, String technicalName, Date expiresOn, String description, Boolean inverted,
			Set<Customer> customers) {
		super();
		this.displayName = displayName;
		this.technicalName = technicalName;
		this.expiresOn = expiresOn;
		this.description = description;
		this.inverted = inverted;
		this.customers = customers;
		this.setArchived(false);
	}
	public FeatureToggle() {
		super();
	}



	public long getId() {
		return id;
	}
	public void setId(long id) {
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
	public Set<Customer> getCustomers() {
		return customers;
	}
	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
	
	public List<String> getCustomersId() {
		if(CollectionUtils.isEmpty(this.getCustomers())) {
			return null;
		}
		List<String> ids = new ArrayList<String>();
		for (Customer customer : this.getCustomers()) {
			ids.add(customer.getId().toString());
		}
		return ids;
	}
	public Boolean getArchived() {
		return archived;
	}
	public void setArchived(Boolean archived) {
		this.archived = archived;
	}
	
	public Boolean isExpired() {
		if(this.expiresOn == null)
			return false;
		return new Date().after(expiresOn);
	}
}
