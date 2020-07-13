package com.cardreader.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardreader.entities.UserProfileDetails;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserProfileDetails, Long> {

}
