package com.takehome.featuretoggle.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.takehome.featuretoggle.api.entity.FeatureToggle;

@Repository
public interface FeatureToggleRepository extends CrudRepository<FeatureToggle, Long>{

	@Query("Select f from FeatureToggle f where f.technicalName = :technicalNames")
	Optional<FeatureToggle> findByTechnicalName(@Param("technicalNames") String technicalNames);
}
