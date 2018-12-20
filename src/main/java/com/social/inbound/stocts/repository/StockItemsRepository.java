package com.social.inbound.stocts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.inbound.stocts.entities.AurItemsMaster;


public interface StockItemsRepository extends JpaRepository<AurItemsMaster, Long> {

}
