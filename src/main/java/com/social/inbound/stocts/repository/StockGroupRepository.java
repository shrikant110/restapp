package com.social.inbound.stocts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.inbound.stocts.entities.AurGroupMaster;

@Repository
public interface StockGroupRepository extends JpaRepository<AurGroupMaster, Long> {
	
	List<AurGroupMaster> findByLanguageType(String language);


}