package com.sbs.vc.datapro.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbs.vc.datapro.auth.model.UserProfileDetails;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserProfileDetails, Long> {

}
