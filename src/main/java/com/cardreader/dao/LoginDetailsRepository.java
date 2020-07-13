package com.cardreader.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cardreader.entities.UserLoginDetails;

@Repository
public interface LoginDetailsRepository extends JpaRepository<UserLoginDetails, Long> {

}
