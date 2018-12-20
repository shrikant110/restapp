package com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.entities.UserCredentialDetails;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredentialDetails, Long> {

}
