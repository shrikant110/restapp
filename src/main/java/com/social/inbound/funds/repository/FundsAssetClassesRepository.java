package com.social.inbound.funds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.inbound.funds.entities.FundsAssetClassesCheckListMaster;

@Repository
public interface FundsAssetClassesRepository extends JpaRepository<FundsAssetClassesCheckListMaster, Long> {

}
