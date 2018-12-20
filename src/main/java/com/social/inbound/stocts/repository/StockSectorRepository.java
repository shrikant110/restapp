package com.social.inbound.stocts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.inbound.stocts.entities.AurSectorMaster;
import com.social.inbound.stocts.entities.AurTextIdMaster;

public interface StockSectorRepository extends JpaRepository<AurSectorMaster, Long> {

}



