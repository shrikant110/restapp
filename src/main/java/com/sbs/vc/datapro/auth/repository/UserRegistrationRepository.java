package com.sbs.vc.datapro.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sbs.vc.datapro.auth.model.UserRegistration;

@Repository
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {
	public UserRegistration findOneByUsername(String username);
}
