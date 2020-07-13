package com.cardreader.inbound.funds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardreader.inbound.funds.entities.FundTextsCheckListMaster;
@Repository
public interface FundTextsRepository extends JpaRepository<FundTextsCheckListMaster, Long> {

}