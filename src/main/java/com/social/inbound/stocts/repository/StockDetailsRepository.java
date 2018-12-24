package com.social.inbound.stocts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.social.inbound.stocts.entities.AuroraStockDetails;

@Repository
public interface StockDetailsRepository  extends JpaRepository<AuroraStockDetails, Long> {

}
