package com.social.inbound.stocts.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.inbound.stocts.entities.AurTextIdMaster;

public interface StockTextRepository extends JpaRepository<AurTextIdMaster, Long> {

}