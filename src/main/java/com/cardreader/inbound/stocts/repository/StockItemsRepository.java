package com.cardreader.inbound.stocts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cardreader.inbound.stocts.entities.AurItemsMaster;


public interface StockItemsRepository extends JpaRepository<AurItemsMaster, Long> {
	List<AurItemsMaster> findByLanguageType(String language);
}
