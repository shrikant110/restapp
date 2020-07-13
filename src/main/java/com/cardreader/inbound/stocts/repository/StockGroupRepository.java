package com.cardreader.inbound.stocts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardreader.inbound.stocts.entities.AurGroupMaster;

@Repository
public interface StockGroupRepository extends JpaRepository<AurGroupMaster, Long> {
	
	List<AurGroupMaster> findByLanguageType(String language);


}