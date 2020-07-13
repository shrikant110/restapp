package com.sbs.vc.datapro.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbs.vc.datapro.auth.model.SubscriptionMaster;

public interface SubscriptionMasterRepository extends JpaRepository<SubscriptionMaster, Long> {
	SubscriptionMaster findOneByType(String type);
	
	@Query( "select o from SubscriptionMaster o where subscriptionId not in (99, 100)")
	List<SubscriptionMaster> getSubscriptionMaster();
	

}
