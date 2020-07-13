package com.sbs.vc.datapro.auth.repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sbs.vc.datapro.auth.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);
    
    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUsername(String username);
    
    Optional<User> findOneByUsernameIgnoreCase(String username);

    Boolean existsByUsername(String username);
    
    Boolean existsByEmail(String email);
    
    List<User> findAll();
    
    @Query( "select o from User o where createdAt BETWEEN :startDate AND :endDate ")
	List<User> getQualifiedUsers( @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
	
}
