package com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.entities.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}
