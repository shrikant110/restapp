package com.cardreader.inbound.stocts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cardreader.inbound.stocts.entities.AurTextIdMaster;

public interface StockTextRepository extends JpaRepository<AurTextIdMaster, Long> {
	List<AurTextIdMaster> findByLanguageType(String language);
}