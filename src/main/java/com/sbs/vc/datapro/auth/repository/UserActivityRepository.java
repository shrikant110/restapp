/**
 * 
 */
package com.sbs.vc.datapro.auth.repository;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sbs.vc.datapro.auth.model.UserActivity;

/**
 * 
 * @author shrikant kushwaha
 *
 */
public interface UserActivityRepository extends JpaRepository<UserActivity, Long>  {
	
	
	@Query( "select o from UserActivity o where action_Time between :startDate and :endDate order by id")
	List<UserActivity> findUserActivityByDate(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);
		
	//List<UserActivity> findAllByaction_TimeLessThanEqualAndEndDateGreaterThanEqual(Timestamp endDate,Timestamp startDate);

}
