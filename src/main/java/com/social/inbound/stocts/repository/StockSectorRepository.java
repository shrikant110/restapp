package com.social.inbound.stocts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.inbound.stocts.entities.AurSectorMaster;

public interface StockSectorRepository extends JpaRepository<AurSectorMaster, Long> {
	List<AurSectorMaster> findByLanguageType(String language);
}



