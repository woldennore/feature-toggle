package com.takehome.featuretoggle.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.takehome.featuretoggle.api.entity.Customer;

@Repository
public interface CustomerRepository  extends CrudRepository<Customer, Long>{

}
