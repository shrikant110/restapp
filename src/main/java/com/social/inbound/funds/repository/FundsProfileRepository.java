package com.social.inbound.funds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.inbound.funds.entities.FundsProfileCheckListMaster;

@Repository
public interface FundsProfileRepository extends JpaRepository<FundsProfileCheckListMaster, Long> {

}
