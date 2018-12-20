package com.social.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.social.entities.Roles;
@Repository
public interface RoleRepository extends JpaRepository<Roles, Long> {

}
