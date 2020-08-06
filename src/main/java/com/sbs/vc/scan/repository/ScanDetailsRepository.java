package com.sbs.vc.scan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sbs.vc.scan.domain.ScanDetailMaster;

@Repository
public interface ScanDetailsRepository extends JpaRepository<ScanDetailMaster, Long>{
	
	@Query(value = "select SCAN_DET_ID,NAME,DESIGNATION,COUNTRY FROM SCAN_DET where name like %?1% or MOBILE_NO like ?2% or COUNTRY like %?3%", nativeQuery = true)
	public List<Object[]> getAutosuggestion(String searchString,String searchString1,String searchString2);

	
	public List<ScanDetailMaster> findByCipharTextContainingIgnoreCaseAndStatus(String cipharText,String status);
	
	public List<ScanDetailMaster> findByCategoryAndCountryContainingIgnoreCaseAndStatus(String category,String cipharText,String status);
	
	public List<ScanDetailMaster> findByCategoryAndNameContainingIgnoreCaseAndStatus(String category,String cipharText,String status);
	
	public List<ScanDetailMaster> findByCategoryAndAddressContainingIgnoreCaseAndStatus(String category,String cipharText,String status);
	
	public List<ScanDetailMaster> findByCategoryAndCipharTextContainingIgnoreCaseAndStatus(String category,String cipharText,String status);
	
	public List<ScanDetailMaster> findByStateContainingIgnoreCaseAndStatus(String cipharText,String status);
	
	public List<ScanDetailMaster> findByCityContainingIgnoreCaseAndStatus(String cipharText,String status);
	
	public List<ScanDetailMaster> findByNameContainingIgnoreCaseAndStatus(String cipharText,String status);
	
	public List<ScanDetailMaster> findByAddressContainingIgnoreCaseAndStatus(String cipharText,String status);
	
	public List<ScanDetailMaster> findByCountryContainingIgnoreCaseAndStatus(String cipharText,String status);
	
}
