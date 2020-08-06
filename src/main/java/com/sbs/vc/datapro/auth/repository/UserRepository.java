package com.sbs.vc.datapro.auth.repository;

import java.sql.Timestamp;
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

    Optional<User> findByUserIdOrEmail(String userId, String email);
    
    List<User> findByIdIn(List<Long> userIds);

    Optional<User> findByUserId(String userId);
    
    Optional<User> findOneByUserIdIgnoreCase(String userId);

    Boolean existsByUserId(String userId);
    
    Boolean existsByEmail(String email);
    
    List<User> findAll();
    
    @Query( "select o from User o where createdAt BETWEEN :startDate AND :endDate ")
	List<User> getQualifiedUsers( @Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
	
}
