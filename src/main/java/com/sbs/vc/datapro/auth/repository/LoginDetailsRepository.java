package com.sbs.vc.datapro.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbs.vc.datapro.auth.model.UserLoginDetails;

@Repository
public interface LoginDetailsRepository extends JpaRepository<UserLoginDetails, Long> {

}
