package com.cardreader.inbound.stocts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cardreader.inbound.stocts.entities.AuroraStockDetails;



@Repository
public interface StockDetailsRepository  extends JpaRepository<AuroraStockDetails, Long> {

	
	public static final String FIND_CURRENCYCODE = "SELECT DISTINCT CCY_CODE FROM AUR_STOCK_DETAIL";

	@Query(value = FIND_CURRENCYCODE, nativeQuery = true)
	public List<Object[]> findCurrencyCodes();
	
	
	
}
